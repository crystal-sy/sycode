package com.sltg.controller.news;

import com.sltg.common.annotation.Log;
import com.sltg.common.constant.UserConstants;
import com.sltg.common.core.controller.BaseController;
import com.sltg.common.core.domain.AjaxResult;
import com.sltg.common.core.domain.entity.UserNews;
import com.sltg.common.core.domain.model.LoginUser;
import com.sltg.common.core.page.TableDataInfo;
import com.sltg.common.enums.BusinessType;
import com.sltg.common.utils.SecurityUtils;
import com.sltg.common.utils.ServletUtils;
import com.sltg.common.utils.poi.ExcelUtil;
import com.sltg.framework.web.service.TokenService;
import com.sltg.system.service.IUserNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 知识库信息
 * 
 * @author styra~
 */
@RestController
@RequestMapping("/user/management")
public class UserManagementController extends BaseController {
    @Autowired
    private IUserNewsService userNewsService;

    @Autowired
    private TokenService tokenService;

    /**
     * 获取用户新闻列表
     */
    @PreAuthorize("@ss.hasPermi('user:news:list')")
    @GetMapping("/list")
    public TableDataInfo userNewsList(UserNews news) {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        news.setUserId(loginUser.getUser().getUserId());
        List<UserNews> list = userNewsService.selectUserNewsList(news);
        return getDataTable(list);
    }

    /**
     * 根据新闻编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('user:news:query')")
    @GetMapping(value = { "/detail", "/{newsId}" })
    public AjaxResult getUserNews(@PathVariable(value = "newsId", required = false) Long newsId) {
        AjaxResult ajax = AjaxResult.success();
        ajax.put(AjaxResult.DATA_TAG, userNewsService.queryUserNewsById(newsId));
        return ajax;
    }

    /**
     * 新增用户新闻
     */
    @PreAuthorize("@ss.hasPermi('user:news:add')")
    @Log(title = "新闻管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addNews(@Validated @RequestBody UserNews news) {
        if (UserConstants.NOT_UNIQUE.equals(userNewsService.checkUserNewsUnique(news.getNewsTitle()))) {
            return AjaxResult.error("新增用户新闻'" + news.getNewsTitle() + "'失败，该新闻已存在");
        }

        news.setCreateBy(SecurityUtils.getUsername());
        return toAjax(userNewsService.insertUserNews(news));
    }

    /**
     * 修改用户新闻
     */
    @PreAuthorize("@ss.hasPermi('user:news:edit')")
    @Log(title = "新闻管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult editNews(@Validated @RequestBody UserNews news) {
        if (userNewsService.queryUserNewsById(news.getNewsId()) == null) {
            return AjaxResult.error("修改用户新闻失败，该新闻不存在");
        }

        news.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userNewsService.updateUserNews(news));
    }

    /**
     * 删除用户新闻
     */
    @PreAuthorize("@ss.hasPermi('user:news:remove')")
    @Log(title = "新闻管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{newsIds}")
    public AjaxResult remove(@PathVariable Long[] newsIds) {
        return toAjax(userNewsService.deleteUserNewsByIds(newsIds));
    }

    @Log(title = "新闻管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('user:news:export')")
    @GetMapping("/export")
    public AjaxResult export(UserNews news) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        news.setUserId(loginUser.getUser().getUserId());
        List<UserNews> list = userNewsService.selectUserNewsList(news);
        ExcelUtil<UserNews> util = new ExcelUtil<>(UserNews.class);
        return util.exportExcel(list, "用户新闻数据");
    }

    @Log(title = "新闻管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('user:news:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<UserNews> util = new ExcelUtil<>(UserNews.class);
        List<UserNews> newsList = util.importExcel(file.getInputStream());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String message = userNewsService.importUserNews(newsList, loginUser.getUsername());
        return AjaxResult.success(message);
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<UserNews> util = new ExcelUtil<>(UserNews.class);
        return util.importTemplateExcel("用户新闻数据");
    }
}

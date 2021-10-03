package com.sltg.controller.news;

import com.sltg.common.core.controller.BaseController;
import com.sltg.common.core.domain.AjaxResult;
import com.sltg.common.core.domain.entity.SysNews;
import com.sltg.common.core.page.TableDataInfo;
import com.sltg.system.service.ISysNewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 新闻信息
 * 
 * @author styra~
 */
@RestController
@RequestMapping("/system/news")
public class SysNewsController extends BaseController {
    @Autowired
    private ISysNewsService newsService;

    /**
     * 获取新闻列表
     */
    @PreAuthorize("@ss.hasPermi('system:news:list')")
    @GetMapping("/list")
    public TableDataInfo newsList(SysNews news) {
        startPage();
        List<SysNews> list = newsService.selectNewsList(news);
        return getDataTable(list);
    }

    /**
     * 根据新闻编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:news:query')")
    @GetMapping(value = { "/detail", "/{newsId}" })
    public AjaxResult getNewsInfo(@PathVariable(value = "newsId", required = false) Long newsId) {
        AjaxResult ajax = AjaxResult.success();
        ajax.put(AjaxResult.DATA_TAG, newsService.queryNewsById(newsId));
        return ajax;
    }
}

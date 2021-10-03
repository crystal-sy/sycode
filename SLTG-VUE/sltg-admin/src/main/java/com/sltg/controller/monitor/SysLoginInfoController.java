package com.sltg.controller.monitor;

import java.util.List;

import com.sltg.system.domain.SysLoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sltg.common.annotation.Log;
import com.sltg.common.core.controller.BaseController;
import com.sltg.common.core.domain.AjaxResult;
import com.sltg.common.core.page.TableDataInfo;
import com.sltg.common.enums.BusinessType;
import com.sltg.common.utils.poi.ExcelUtil;
import com.sltg.system.service.ISysLoginInfoService;

/**
 * 系统访问记录
 * 
 * @author styra~
 */
@RestController
@RequestMapping("/monitor/loginInfo")
public class SysLoginInfoController extends BaseController {
    @Autowired
    private ISysLoginInfoService loginInfoService;

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLoginInfo loginInfo) {
        startPage();
        List<SysLoginInfo> list = loginInfoService.selectLoginInfoList(loginInfo);
        return getDataTable(list);
    }

    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('monitor:logininfor:export')")
    @GetMapping("/export")
    public AjaxResult export(SysLoginInfo loginInfo) {
        List<SysLoginInfo> list = loginInfoService.selectLoginInfoList(loginInfo);
        ExcelUtil<SysLoginInfo> util = new ExcelUtil<>(SysLoginInfo.class);
        return util.exportExcel(list, "登录日志");
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds) {
        return toAjax(loginInfoService.deleteLoginInfoByIds(infoIds));
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登录日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean() {
        loginInfoService.cleanLoginInfo();
        return AjaxResult.success();
    }
}

package com.sltg.controller.monitor;

import java.util.List;
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
import com.sltg.system.domain.SysOperateLog;
import com.sltg.system.service.ISysOperateLogService;

/**
 * 操作日志记录
 * 
 * @author styra~
 */
@RestController
@RequestMapping("/monitor/operateLog")
public class SysOperateLogController extends BaseController {
    @Autowired
    private ISysOperateLogService operateLogService;

    @PreAuthorize("@ss.hasPermi('monitor:operlog:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOperateLog operateLog) {
        startPage();
        List<SysOperateLog> list = operateLogService.selectOperateLogList(operateLog);
        return getDataTable(list);
    }

    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('monitor:operlog:export')")
    @GetMapping("/export")
    public AjaxResult export(SysOperateLog operLog) {
        List<SysOperateLog> list = operateLogService.selectOperateLogList(operLog);
        ExcelUtil<SysOperateLog> util = new ExcelUtil<>(SysOperateLog.class);
        return util.exportExcel(list, "操作日志");
    }

    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
    @DeleteMapping("/{operateIds}")
    public AjaxResult remove(@PathVariable Long[] operateIds) {
        return toAjax(operateLogService.deleteOperateLogByIds(operateIds));
    }

    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
    @DeleteMapping("/clean")
    public AjaxResult clean() {
        operateLogService.cleanOperateLog();
        return AjaxResult.success();
    }
}

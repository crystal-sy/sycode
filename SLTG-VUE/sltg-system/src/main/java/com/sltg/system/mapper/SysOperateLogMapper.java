package com.sltg.system.mapper;

import java.util.List;
import com.sltg.system.domain.SysOperateLog;

/**
 * 操作日志 数据层
 * 
 * @author styra~
 */
public interface SysOperateLogMapper {
    /**
     * 新增操作日志
     * 
     * @param operateLog 操作日志对象
     */
    void insertOperateLog(SysOperateLog operateLog);

    /**
     * 查询系统操作日志集合
     * 
     * @param operateLog 操作日志对象
     * @return 操作日志集合
     */
    List<SysOperateLog> selectOperateLogList(SysOperateLog operateLog);

    /**
     * 批量删除系统操作日志
     * 
     * @param operateIds 需要删除的操作日志ID
     * @return 结果
     */
    int deleteOperateLogByIds(Long[] operateIds);

    /**
     * 查询操作日志详细
     * 
     * @param operateId 操作ID
     * @return 操作日志对象
     */
    SysOperateLog selectOperateLogById(Long operateId);

    /**
     * 清空操作日志
     */
    void cleanOperateLog();
}

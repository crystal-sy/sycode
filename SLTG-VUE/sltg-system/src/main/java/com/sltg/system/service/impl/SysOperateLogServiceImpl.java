package com.sltg.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sltg.system.domain.SysOperateLog;
import com.sltg.system.mapper.SysOperateLogMapper;
import com.sltg.system.service.ISysOperateLogService;

/**
 * 操作日志 服务层处理
 * 
 * @author styra~
 */
@Service
public class SysOperateLogServiceImpl implements ISysOperateLogService {
    @Autowired
    private SysOperateLogMapper operateLogMapper;

    /**
     * 新增操作日志
     * 
     * @param operateLog 操作日志对象
     */
    @Override
    public void insertOperateLog(SysOperateLog operateLog) {
        operateLogMapper.insertOperateLog(operateLog);
    }

    /**
     * 查询系统操作日志集合
     * 
     * @param operateLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperateLog> selectOperateLogList(SysOperateLog operateLog) {
        return operateLogMapper.selectOperateLogList(operateLog);
    }

    /**
     * 批量删除系统操作日志
     * 
     * @param operateIds 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public int deleteOperateLogByIds(Long[] operateIds) {
        return operateLogMapper.deleteOperateLogByIds(operateIds);
    }

    /**
     * 查询操作日志详细
     * 
     * @param operateId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperateLog selectOperateLogById(Long operateId) {
        return operateLogMapper.selectOperateLogById(operateId);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperateLog() {
        operateLogMapper.cleanOperateLog();
    }
}

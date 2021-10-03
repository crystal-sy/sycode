package com.sltg.system.service.impl;

import com.sltg.common.core.domain.entity.SysNewsKnowledge;
import com.sltg.system.mapper.SysNewsKnowledgeMapper;
import com.sltg.system.service.ISysKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告 服务层实现
 * 
 * @author styra~
 */
@Service
public class SysNewsKnowledgeServiceImpl implements ISysKnowledgeService {
    @Autowired
    private SysNewsKnowledgeMapper newsKnowledgeMapper;

    @Override
    public List<SysNewsKnowledge> selectKnowledgeList(SysNewsKnowledge news) {
        return newsKnowledgeMapper.selectNewsKnowledgeList(news);
    }

    @Override
    public SysNewsKnowledge queryKnowledgeById(Long newsId) {
        return newsKnowledgeMapper.queryNewsKnowledgeById(newsId);
    }
}

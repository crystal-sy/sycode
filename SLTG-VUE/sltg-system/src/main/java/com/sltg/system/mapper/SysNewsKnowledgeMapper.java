package com.sltg.system.mapper;

import com.sltg.common.core.domain.entity.SysNewsKnowledge;

import java.util.List;

/**
 * 新闻知识库表 数据层
 * 
 * @author styra~
 */
public interface SysNewsKnowledgeMapper {
    /**
     * 查询新闻知识库列表
     *
     * @param news 新闻知识库信息
     * @return 新闻知识库集合
     */
    List<SysNewsKnowledge> selectNewsKnowledgeList(SysNewsKnowledge news);

    /**
     * 查询新闻知识库信息
     *
     * @param newsId 新闻知识库ID
     * @return 新闻知识库信息
     */
    SysNewsKnowledge queryNewsKnowledgeById(Long newsId);

}

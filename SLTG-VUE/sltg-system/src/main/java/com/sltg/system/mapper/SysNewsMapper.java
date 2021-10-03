package com.sltg.system.mapper;

import com.sltg.common.core.domain.entity.SysNews;
import com.sltg.system.domain.SysNotice;

import java.util.List;

/**
 * 新闻信息表 数据层
 * 
 * @author styra~
 */
public interface SysNewsMapper {
    /**
     * 查询新闻列表
     *
     * @param news 新闻信息
     * @return 新闻集合
     */
    List<SysNews> selectNewsList(SysNews news);

    /**
     * 查询新闻信息
     *
     * @param newsId 新闻ID
     * @return 新闻信息
     */
    SysNews queryNewsById(Long newsId);

}

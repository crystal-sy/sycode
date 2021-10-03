package com.sltg.system.service.impl;

import com.sltg.common.core.domain.entity.SysNews;
import com.sltg.system.mapper.SysNewsMapper;
import com.sltg.system.service.ISysNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告 服务层实现
 * 
 * @author styra~
 */
@Service
public class SysNewsServiceImpl implements ISysNewsService {
    @Autowired
    private SysNewsMapper newsMapper;

    @Override
    public List<SysNews> selectNewsList(SysNews news) {
        return newsMapper.selectNewsList(news);
    }

    @Override
    public SysNews queryNewsById(Long newsId) {
        return newsMapper.queryNewsById(newsId);
    }
}

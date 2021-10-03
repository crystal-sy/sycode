package com.sltg.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sltg.common.annotation.Excel;
import com.sltg.common.annotation.Excel.ColumnType;
import com.sltg.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 新闻知识库信息 sys_news_knowledge
 * 
 * @author styra~
 */
public class SysNewsKnowledge extends BaseEntity {
    private static final long serialVersionUID = -579668501089482074L;

    @Excel(name = "新闻编号", cellType = ColumnType.NUMERIC, prompt = "新闻编号")
    private Long newsId;

    @Excel(name = "新闻标题")
    private String newsTitle;

    @Excel(name = "新闻分类")
    private String newsType;

    @Excel(name = "检测类型")
    private String detectionType;

    @Excel(name = "新闻内容")
    private String newsInfo;

    @Excel(name = "新闻内容路径")
    private String newsPath;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public SysNewsKnowledge() {}

    public SysNewsKnowledge(Long newsId) {
        this.newsId = newsId;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getDetectionType() {
        return detectionType;
    }

    public void setDetectionType(String detectionType) {
        this.detectionType = detectionType;
    }

    public String getNewsInfo() {
        return newsInfo;
    }

    public void setNewsInfo(String newsInfo) {
        this.newsInfo = newsInfo;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNewsPath() {
        return newsPath;
    }

    public void setNewsPath(String newsPath) {
        this.newsPath = newsPath;
    }
}

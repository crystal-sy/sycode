package com.sltg.system.service;

import com.sltg.common.core.domain.entity.UserNews;

import java.util.List;

/**
 * 用户新闻 业务层
 * 
 * @author styra~
 */
public interface IUserNewsService {
    /**
     * 根据条件分页查询新闻列表
     * 
     * @param news 新闻信息
     * @return 新闻信息集合信息
     */
    List<UserNews> selectUserNewsList(UserNews news);

    /**
     * 通过新闻ID查询新闻详情
     * 
     * @param newsId 新闻ID
     * @return 新闻对象信息
     */
    UserNews queryUserNewsById(Long newsId);

    /**
     * 校验新闻名称是否唯一
     *
     * @param newsTitle 新闻名称
     * @return 结果
     */
    String checkUserNewsUnique(String newsTitle);

    /**
     * 新增用户新闻信息
     *
     * @param news 用户新闻信息
     * @return 结果
     */
    int insertUserNews(UserNews news);

    /**
     * 修改用户新闻信息
     *
     * @param news 用户新闻信息
     * @return 结果
     */
    int updateUserNews(UserNews news);

    /**
     * 批量删除用户新闻信息
     *
     * @param newsIds 需要删除的新闻ID
     * @return 结果
     */
    int deleteUserNewsByIds(Long[] newsIds);

    /**
     * 导入用户新闻数据
     *
     * @param newsList 用户新闻数据列表
     * @param operateName 操作用户
     * @return 结果
     */
    String importUserNews(List<UserNews> newsList, String operateName);
}

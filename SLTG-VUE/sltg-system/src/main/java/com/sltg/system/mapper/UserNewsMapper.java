package com.sltg.system.mapper;

import com.sltg.common.core.domain.entity.UserNews;

import java.util.List;

/**
 * 用户新闻表 数据层
 * 
 * @author styra~
 */
public interface UserNewsMapper {
    /**
     * 查询用户新闻列表
     *
     * @param news 用户新闻信息
     * @return 用户新闻集合
     */
    List<UserNews> selectUserNewsList(UserNews news);

    /**
     * 查询用户新闻信息
     *
     * @param newsId 用户新闻ID
     * @return 用户新闻信息
     */
    UserNews queryUserNewsById(Long newsId);

    /**
     * 校验新闻名称是否唯一
     *
     * @param newsTitle 新闻名称
     * @return 结果
     */
    UserNews checkUserNewsUnique(String newsTitle);

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

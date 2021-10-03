package com.sltg.system.service.impl;

import com.sltg.common.constant.UserConstants;
import com.sltg.common.core.domain.entity.UserNews;
import com.sltg.common.exception.CustomException;
import com.sltg.common.utils.StringUtils;
import com.sltg.system.mapper.UserNewsMapper;
import com.sltg.system.service.IUserNewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告 服务层实现
 * 
 * @author styra~
 */
@Service
public class UserNewsServiceImpl implements IUserNewsService {
    private static final Logger logger = LoggerFactory.getLogger(UserNewsServiceImpl.class);

    @Autowired
    private UserNewsMapper userNewsMapper;

    @Override
    public List<UserNews> selectUserNewsList(UserNews news) {
        return userNewsMapper.selectUserNewsList(news);
    }

    @Override
    public UserNews queryUserNewsById(Long newsId) {
        return userNewsMapper.queryUserNewsById(newsId);
    }

    @Override
    public String checkUserNewsUnique(String newsTitle) {
        UserNews userNews = userNewsMapper.checkUserNewsUnique(newsTitle);
        if (userNews != null) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int insertUserNews(UserNews news) {
        return userNewsMapper.insertUserNews(news);
    }

    @Override
    public int updateUserNews(UserNews news) {
        return userNewsMapper.updateUserNews(news);
    }

    @Override
    public int deleteUserNewsByIds(Long[] newsIds) {
        return userNewsMapper.deleteUserNewsByIds(newsIds);
    }

    @Override
    public String importUserNews(List<UserNews> newsList, String operateName) {
        if (StringUtils.isNull(newsList) || newsList.size() == 0) {
            throw new CustomException("导入用户新闻数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (UserNews news : newsList) {
            try {
                // 验证是否存在这个新闻
                UserNews userNews = userNewsMapper.checkUserNewsUnique(news.getNewsTitle());
                if (StringUtils.isNull(userNews)) {
                    news.setCreateBy(operateName);
                    this.insertUserNews(news);
                    successNum++;
                    successMsg.append("<br/>")
                        .append(successNum).append("、新闻 ").append(news.getNewsTitle()).append(" 导入成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>")
                        .append(failureNum).append("、新闻 ").append(news.getNewsTitle()).append(" 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、新闻 " + news.getNewsTitle() + " 导入失败：";
                failureMsg.append(msg).append(e.getMessage());
                logger.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}

package com.sltg.framework.security.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sltg.framework.manager.AsyncManager;
import com.sltg.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import com.alibaba.fastjson.JSON;
import com.sltg.common.constant.Constants;
import com.sltg.common.constant.HttpStatus;
import com.sltg.common.core.domain.AjaxResult;
import com.sltg.common.core.domain.model.LoginUser;
import com.sltg.common.utils.ServletUtils;
import com.sltg.common.utils.StringUtils;
import com.sltg.framework.manager.factory.AsyncFactory;

/**
 * 自定义退出处理类 返回成功
 * 
 * @author styra~
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(userName, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
    }
}

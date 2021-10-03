package com.sltg.framework.manager.factory;

import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sltg.common.constant.Constants;
import com.sltg.common.utils.LogUtils;
import com.sltg.common.utils.ServletUtils;
import com.sltg.common.utils.ip.AddressUtils;
import com.sltg.common.utils.ip.IpUtils;
import com.sltg.common.utils.spring.SpringUtils;
import com.sltg.system.domain.SysLoginInfo;
import com.sltg.system.domain.SysOperateLog;
import com.sltg.system.service.ISysLoginInfoService;
import com.sltg.system.service.ISysOperateLogService;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * 异步工厂（产生任务用）
 * 
 * @author styra~
 */
public class AsyncFactory {
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 记录登录信息
     * 
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return 任务task
     */
    public static TimerTask recordLoginInfo(final String username, final String status, final String message,
        final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtils.getRealAddressByIP(ip);
                // 打印信息到日志
                String stringBuilder = LogUtils.getBlock(ip) + address +
                    LogUtils.getBlock(username) +
                    LogUtils.getBlock(status) +
                    LogUtils.getBlock(message);
                sys_user_logger.info(stringBuilder, args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLoginInfo loginInfo = new SysLoginInfo();
                loginInfo.setUserName(username);
                loginInfo.setIpaddr(ip);
                loginInfo.setLoginLocation(address);
                loginInfo.setBrowser(browser);
                loginInfo.setOs(os);
                loginInfo.setMsg(message);
                // 日志状态
                if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)) {
                    loginInfo.setStatus(Constants.SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(status)) {
                    loginInfo.setStatus(Constants.FAIL);
                }
                // 插入数据
                SpringUtils.getBean(ISysLoginInfoService.class).insertLoginInfo(loginInfo);
            }
        };
    }

    /**
     * 操作日志记录
     * 
     * @param operateLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOperate(final SysOperateLog operateLog) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                operateLog.setOperateLocation(AddressUtils.getRealAddressByIP(operateLog.getOperateIp()));
                SpringUtils.getBean(ISysOperateLogService.class).insertOperateLog(operateLog);
            }
        };
    }
}

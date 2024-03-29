package com.gx.sbd.interceptors;


import com.alibaba.fastjson.JSON;
import com.gx.demo.utils.BaseResponse;
import com.gx.demo.utils.CommUtil;
import com.gx.sbd.annotation.NeedLogin;
import com.gx.sbd.demo.LoginInfo;
import com.gx.sbd.demo.ThreadLocalContext;
import com.gx.sbd.helper.ResponseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @ClassName : BaseInterceptor
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/18 11:00
 * @Version : 1.0
 */
@Configuration
public class BaseInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);


    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        ThreadLocalContext.removeLocal();
    }


    /**
     * This implementation always returns {@code true}.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        Long sessionId = CommUtil.getWorkId();
        logger.info("生成sessionId: "+sessionId);
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setSessionId(sessionId.toString());
        loginInfo.setName(request.getRequestURI());
        ThreadLocalContext.setLocal(loginInfo);

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        NeedLogin needLogin = method.getAnnotation(NeedLogin.class);
        if(null != needLogin){
            logger.info("登陆校验");
            BaseResponse msg = BaseResponse.newInstance();
            msg.fail("登陆校验哦");
            ResponseHelper.wirterJosn(response, JSON.toJSONString(msg));
            return false;
        }

        return true;
    }

}

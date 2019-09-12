package com.gx.sbd.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName : BaseFilter
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/12 10:55
 * @Version : 1.0
 */
@Component
@WebFilter(filterName = "BaseFilter",urlPatterns = "/*",asyncSupported = true)
public class BaseFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(BaseFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("BaseFilter init......");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String path = httpServletRequest.getServletPath();
        //由于我的@WebFilter注解配置的是urlPatterns="/*"(过滤所有请求),所以这里对不需要过滤的静态资源url,作忽略处理(大家可以依照具体需求配置)
        String[] exclusionsUrls = {".js",".gif",".jpg",".png",".css",".ico"};
        for (String str : exclusionsUrls) {
            if (path.contains(str)) {
                chain.doFilter(request,response);
                return;
            }
        }
        chain.doFilter(new XssHttpServletRequestWrapper(httpServletRequest),response);
    }

    @Override
    public void destroy() {
        logger.info("BaseFilter destroy......");
    }
}

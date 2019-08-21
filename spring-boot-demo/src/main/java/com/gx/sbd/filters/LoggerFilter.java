package com.gx.sbd.filters;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import org.springframework.stereotype.Component;

/**
 * @ClassName : LoggerFilter
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/2/12 16:39
 * @Version : 1.0
 */
@Component
public class LoggerFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
       // System.out.println("这是日志："+event.getLoggerName() +" - "+event.getMessage());
        return FilterReply.ACCEPT;
    }

}

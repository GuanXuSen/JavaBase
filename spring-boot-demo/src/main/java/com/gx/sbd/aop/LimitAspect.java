package com.gx.sbd.aop;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;
import com.gx.sbd.annotation.ServiceLimit;
import com.gx.sbd.exception.BaseException;
import com.gx.sbd.utils.IPUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName : LimitAspect
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/10 13:49
 * @Version : 1.0
 */
@Aspect
@Configuration
public class LimitAspect {
    //根据IP分不同的令牌桶, 每天自动清理缓存
    private static LoadingCache<String, RateLimiter> caches = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(1, TimeUnit.DAYS)
            .build(new CacheLoader<String, RateLimiter>() {
                @Override
                public RateLimiter load(String key){
                    // 新的IP初始化 每秒只发出5个令牌
                    return RateLimiter.create(5);
                }
            });

    //Service层切点  限流
    @Pointcut("@annotation(com.gx.sbd.annotation.ServiceLimit)")
    public void ServiceAspect() {}

    @Around("ServiceAspect()")
    public  Object around(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ServiceLimit limitAnnotation = method.getAnnotation(ServiceLimit.class);
        ServiceLimit.LimitType limitType = limitAnnotation.limitType();
        String key = limitAnnotation.key();
        Object obj = null;
        try {
            if(limitType.equals(ServiceLimit.LimitType.IP)){
                key = IPUtil.getIpAddress();
            }
            RateLimiter rateLimiter = caches.get(key);
            Boolean flag = rateLimiter.tryAcquire();
            if(flag){
                obj = joinPoint.proceed();
            }else{
                throw new BaseException("小同志，你访问的太频繁了");
            }
        } catch (Throwable e) {
            throw new BaseException("小同志，你访问的太频繁了");
        }
        return obj;
    }

}

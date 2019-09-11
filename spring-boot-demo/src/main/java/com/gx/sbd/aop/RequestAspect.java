package com.gx.sbd.aop;

import com.alibaba.fastjson.JSON;
import com.gx.demo.utils.BaseResponse;
import com.gx.sbd.utils.IPUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 请求切面
 */

@Aspect
@Component
public class RequestAspect {

    private  Logger logger = LoggerFactory.getLogger(RequestAspect.class);

    @Pointcut("execution(* *..controllers..*.*(..))")
    public void RequestPointCut(){}


    @Before("RequestPointCut()")
    public void doBefore(JoinPoint point){
        try{
            Object[] objects = point.getArgs();
            Object firstObj = objects.length > 0?objects[0] : null;
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("HTTP_METHOD",request.getMethod());
            jsonObject.put("URL",request.getRequestURI());
            jsonObject.put("IP", IPUtil.getIpAddress());
            jsonObject.put("CLASS_METHOD",
                    point.getSignature().getDeclaringTypeName()
                            + " || " +
                           point.getSignature().getName()
            );
            String message = "\n\t request: " + jsonObject.toString();
            if(null != firstObj
                    && !(firstObj instanceof ServletRequest)
                    && !(firstObj instanceof ServletResponse)){
                message += JSON.toJSONString(firstObj);

            }
            logger.info(message);
        } catch (Exception e){
            logger.error("RequestAspect：切面失败 {}",e);
        }
    }


    @AfterReturning(returning = "object",pointcut = "RequestPointCut()")
    public void doAfterReturning(Object object){
        if(null!=object && !(object instanceof ModelAndView)){
            logger.info("\n\t response: {}",JSON.toJSONString(object));
        }
    }


    @Around("RequestPointCut() && args(..,bindingResult)")
    public Object bindingRestAround(ProceedingJoinPoint proceedingJoinPoint, BindingResult bindingResult) throws Throwable {
        logger = LoggerFactory.getLogger(proceedingJoinPoint.getTarget().getClass());
        if(null != bindingResult && bindingResult.hasErrors()){
            String targeName = proceedingJoinPoint.getTarget().getClass().getSimpleName();
            String method = proceedingJoinPoint.getSignature().getName();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            StringBuilder stringBuilder = new StringBuilder(200);
            int index = 0;
            for(FieldError error : fieldErrors){
                logger.error("{} 验证失败，controller：{} , 参数：{}, 属性：{}, 错误：{}, 消息：{},\n\t"
                        ,targeName,method,error.getObjectName(),error.getField(),error.getCode(),error.getDefaultMessage());
                if(index>0){
                    stringBuilder.append(":");
                }
                stringBuilder.append(error.getDefaultMessage());
                index++;
            }
            return BaseResponse.newInstance().fail("参数错误").toResponseMap();
        }else {
            logger.info("验证成功");
            return proceedingJoinPoint.proceed();
        }

    }
}

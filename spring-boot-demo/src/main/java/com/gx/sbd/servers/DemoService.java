package com.gx.sbd.servers;

import com.gx.demo.utils.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName : DemoService
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/8/22 15:33
 * @Version : 1.0
 */
@Component
public class DemoService {

    private static final Logger logger = LoggerFactory.getLogger(DemoService.class);
    /**
     * 测试1
     * @return
     */
    public Object demo1() {
        BaseResponse response = BaseResponse.newInstance();
        try{

        }catch (Exception e){
            logger.error("执行错误",e);
            response.fail(e.getMessage());
        }
        return response;
    }
}

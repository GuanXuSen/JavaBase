package com.gx.sbd.servers;

import com.gx.demo.utils.BaseResponse;
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
    /**
     * 测试1
     * @return
     */
    public Object demo1() {
        BaseResponse response = BaseResponse.newInstance();
        return response;
    }
}

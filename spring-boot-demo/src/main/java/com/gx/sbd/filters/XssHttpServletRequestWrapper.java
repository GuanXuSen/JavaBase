package com.gx.sbd.filters;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;


import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @ClassName : XssHttpServletRequestWrapper
 * @Description :TOO xss 过滤
 * @Author : gx
 * @Date : 2019/8/22 15:40
 * @Version : 1.0
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper{

    private byte[] originBody;

    private byte[] xssBody;

    private String originBodyString;

    private String xssBodyString;

    private JSONObject xssJson;

    private BufferedReader reader;

    private ServletInputStream servletInputStream;


    public XssHttpServletRequestWrapper(HttpServletRequest request) throws IOException{
        super(request);
        initRequestBody(request);
    }

    private void initRequestBody(HttpServletRequest request) throws IOException{
        originBody = IOUtils.toByteArray(request.getInputStream());
        originBodyString = new String(originBody);
        if(StringUtils.isNotBlank(originBodyString)){
            xssJson = buildJsonObject(originBodyString);
        }
    }

    /**
     * 过滤 json
     * @return
     */
    private JSONObject buildJsonObject(String xssJsonString){
        JSONObject jsonObject = new JSONObject();
        JSONObject xssJsonObject = JSONObject.parseObject(xssJsonString);
        return jsonObject;
    }
}

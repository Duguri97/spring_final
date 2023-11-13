package com.study.common.util;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class IPCheckInterceptor extends HandlerInterceptorAdapter {
    private Map<String,Boolean> ipDeniedMap = new HashMap<>();
    public IPCheckInterceptor() {
        for (int i = 3; i < 100; i++) {
            ipDeniedMap.put("192.168.0."+i, true);
        }
        ipDeniedMap.put("192.168.0.17", false);
        ipDeniedMap.put("0:0:0:0:0:0:0:1", false);
        ipDeniedMap.put("127.0.0.1", false);
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
        boolean denied = ipDeniedMap.get(remoteAddr);
        if (denied) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "오지마");
            return false;
        }
        return true;
    }
}

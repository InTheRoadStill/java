package com.example.demo.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@Autowired
	StringRedisTemplate redisTemplate;
    
    ValueOperations<String, String> stringRedis;
    
    @PostConstruct
    public void init() {
    	stringRedis = redisTemplate.opsForValue();
    }
    
	@GetMapping("test")
	@ResponseBody
	public String test(HttpServletRequest request) {
		String ip = getIp(request);
		try {
			stringRedis.set("name", ip);
	        System.out.println(stringRedis.get("name"));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return ip;
	}
	
	@RequestMapping("/hello/themaleaf")
    public String themaleaf(Model model) {
        model.addAttribute("name", "cheng");
        return "hello";
    }
	

	private static String getIp(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
            if("127.0.0.1".equals(ip)||"0:0:0:0:0:0:0:1".equals(ip)){
                //根据网卡取本机配置的IP
                 InetAddress inet=null;
                 try {
                     inet = InetAddress.getLocalHost();
                 } catch (UnknownHostException e) {
                     e.printStackTrace();
                 }
                     ip= inet.getHostAddress();
            }
        }  
        return ip;
    }
}

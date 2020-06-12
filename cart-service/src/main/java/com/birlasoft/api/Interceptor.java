package com.birlasoft.api;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.birlasoft.api.service.CartService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class Interceptor implements HandlerInterceptor{
	static Claims claims ;
    public static String username;
	static String authority;
  public static String token;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String header = request.getHeader("Authorization");
		 token = header.replace("Bearer", "");
		claims = Jwts.parser()
	             .setSigningKey("secret-key".getBytes())
	             .parseClaimsJws(token)
	             .getBody();
		username = claims.getSubject();
		//CartService.getUsername(username);
		System.out.println("--------------------"+username);
        List<Map<String, String>> authoritiesMap=(List<Map<String, String>>) claims.get("auth");
        Map<String,String> map =authoritiesMap.get(0);
        authority =map.get("authority");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	
}


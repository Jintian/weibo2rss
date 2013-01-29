package com.dengjintian.weibo2rss.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 类Log4jFilter的描述：将当前访问者的ip地址塞到mdc里面，方便日志排查.
 * 
 * @author: jintian, Date: 29/1/13
 */
public class Log4jIPFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                                                                                                                      throws ServletException,
                                                                                                                      IOException {
        MDC.put("ip", request.getRemoteAddr());
        filterChain.doFilter(request, response);
    }
}

package com.xuecheng.govern.gateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.xuecheng.govern.gateway.exception.RateLimitException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName RateFilter.java
 * @Description TODO
 * @createTime 2019年04月24日 20:44:00
 * 限流:redis限流器   guava限流
 * 重点:guava是单进程,uava RateLimiter能够处理突发请求(预消费)，
 * 这里rest接口调用频率限制是固定的，不需要更不能使用预消费能力，否则将会导致接口调用失败
 * redis：1.redis效率高，易扩展
 *        2.redis对语言无关，可以更好的接入不同语言开发的系统（异构）
 *        3.redis单进程单线程的特点可以更好的解决最终一致性，多进程间协同控制更为容易
 */
@Component
public class RateFilter extends ZuulFilter {
    //谷歌限流令牌桶
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);
    @Override
    public String filterType() {
        return PRE_TYPE;
    }
   //越小优先级越高
    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER -1;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        //判断是否能获取令牌
        if(!RATE_LIMITER.tryAcquire()){
            throw new RateLimitException();
        }
        return null;
    }
}

package com.qiancheng.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 作者：qiwj
 * 时间：2020/7/10
 */
@Component
public class applicationFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(applicationFilter.class);
    /**
     * 过滤器的类型。可选值有：
     * pre - 前置过滤
     * route - 路由后过滤
     * error - 异常过滤
     * post - 远程服务调用后过滤
     */
    @Override
    public String filterType() {
        return "pre";
    }
    /**
     * 同种类的过滤器的执行顺序。
     * 按照返回值的自然升序执行。
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 返回boolean类型。代表当前filter是否生效。
     * 默认值为false。
     * 返回true代表开启filter。
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }
    /**
     * run方法就是过滤器的具体逻辑。
     * return 可以返回任意的对象，当前实现忽略。（spring-cloud-zuul官方解释）
     * 直接返回null即可。
     */
    @Override
    public Object run() throws ZuulException {
        /*
        // 登录校验逻辑。
        // 1）获取Zuul提供的请求上下文对象
        RequestContext ctx = RequestContext.getCurrentContext();//RequestContext是一个Request域，此域的作用范围是从请求到达zuul一直到路由结束返回到客户端，整个完整流程都会存在
        // 2) 从上下文中获取request对象
        HttpServletRequest req = ctx.getRequest();
        // 3) 从请求中获取token
        String token = req.getParameter("access-token");
        // 4) 判断
        if(StringUtils.isBlank(token)){ //StringUtils,需引apache的commons-lang3包的依赖 使用此方式可以避免内存溢出
            // 没有token，登录校验失败，拦截
            ctx.setSendZuulResponse(false);//true则放行，false则拦截
            // 返回403状态码。也可以考虑重定向到登录页。
            ctx.setResponseStatusCode(HttpStatus.Forbidden.value());
        }
        // 校验通过，可以考虑把用户信息放入上下文，继续向后执行
        return null;//默认为空即为放行
        */
        return null;
    }
}

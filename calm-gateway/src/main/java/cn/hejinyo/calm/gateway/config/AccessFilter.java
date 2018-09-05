package cn.hejinyo.calm.gateway.config;

import cn.hejinyo.calm.common.basis.consts.Constant;
import cn.hejinyo.calm.common.basis.consts.StatusCode;
import cn.hejinyo.calm.common.basis.utils.JsonUtil;
import cn.hejinyo.calm.common.basis.utils.Result;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * 请求过滤器
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/8/18 16:45
 */
@Slf4j
@Component
public class AccessFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
        if (request.getRequestURL().toString().contains(Constant.SERVER_API)) {
            log.warn("不允许外部访问内部接口");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody(JsonUtil.toJson(Result.error(StatusCode.USER_UNAUTHORIZED)));
            ctx.getResponse().setContentType("application/json;charset=UTF-8");
            return null;
        }

        ctx.addZuulRequestHeader(Constant.AUTHOR_PARAM, request.getHeader(Constant.AUTHOR_PARAM));
        log.info("accessToken ok");
        return null;

      /*  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            log.info("=========AccessFilter=========>>{}", authentication.getName());
            RequestContext requestContext = RequestContext.getCurrentContext();
            requestContext.addZuulRequestHeader("x-role-header", authentication.getName());
            requestContext.addZuulRequestHeader("x-role-header", "x-role-header");
        }*/
    }
}

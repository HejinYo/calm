package cn.hejinyo.calm.common.web.utils;

import cn.hejinyo.calm.common.basis.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class WebUtils extends org.springframework.web.util.WebUtils {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 判断是否ajax请求
     */
    public static boolean isAjax(HttpServletRequest request) {
        String accept = request.getHeader("accept");//（客户端）希望接受的数据类型
        //String content = request.getHeader("content-type");//发送端（客户端|服务器）发送的实体数据的数据类型
        String getHeaderX = request.getHeader("X-Requested-With");// AJAX特有的一个参数
        if ((accept != null && accept.equalsIgnoreCase("application/json")) || (getHeaderX != null && getHeaderX.equalsIgnoreCase("XMLHttpRequest"))) {
            return true;
        }
        return false;
    }

    /**
     * 获取IP地址
     * <p>
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;
        log.debug("request.getRemoteHost():" + request.getRemoteHost());
      /*  //取得全部头信息
        Enumeration enu = request.getHeaderNames();
        //以此取出头信息
        while (enu.hasMoreElements()) {
            String headerName = (String) enu.nextElement();
            //取出头信息内容
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + ":" + headerValue);
        }*/
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("x-real-ip");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            //使用代理，则获取第一个IP地址
            if (StringUtils.isEmpty(ip) && ip.length() > 15) {
                if (ip.indexOf(",") > 0) {
                    ip = ip.substring(0, ip.indexOf(","));
                }
            }
        } catch (Exception e) {
            log.error("IPUtils ERROR ", e);
        }
        return ip;
    }

    public static String getIpAddr(ServletRequest request) {
        return getIpAddr((HttpServletRequest) request);
    }

}

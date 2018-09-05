package cn.hejinyo.calm.jelly.config;

import cn.hejinyo.calm.common.basis.consts.Constant;
import cn.hejinyo.calm.common.basis.consts.StatusCode;
import cn.hejinyo.calm.common.basis.utils.Result;
import cn.hejinyo.calm.common.basis.utils.StringUtils;
import cn.hejinyo.calm.common.basis.utils.Tools;
import cn.hejinyo.calm.common.web.shiro.*;
import cn.hejinyo.calm.common.web.utils.ResponseUtils;
import cn.hejinyo.calm.jelly.feign.AuthApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2017/8/17 23:22
 */
@Configuration
@Slf4j
public class JellyShiroConfig extends CalmShiroConfig {

    private static final String JELLY_AUTH_NAME = "jelly";

    /**
     * 自定义realms
     */
    @Override
    @Bean
    protected List<Realm> getRealms() {
        List<Realm> list = new ArrayList<>();
        list.add(new CalmAuthRealm());
        return list;
    }

    /**
     * 注入自定义拦截器,注意拦截器自注入问题
     */
    @Override
    @Bean
    protected Map<String, Filter> getFilters() {
        Map<String, Filter> list = new HashMap<>();
        list.put(JELLY_AUTH_NAME, authFilter());
        return list;
    }

    /**
     * 拦截器链
     */
    @Override
    protected Map<String, String> getFilterChainDefinitionMap() {
        // 拦截器链
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterAnonConfig().getPath().forEach(path -> {
            log.info("jelly 不拦截路径 => {}", path);
            filterMap.put(path, "anon");
        });
        // 不拦截内部服务调用
        filterMap.put(Constant.SERVER_API_ANON, "anon");

        filterMap.put("/**", JELLY_AUTH_NAME);
        return filterMap;
    }

    @Bean
    public FilterAnonConfig filterAnonConfig() {
        return new FilterAnonConfig();
    }

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

    /**
     * 解决自定义拦截器混乱问题
     */
    @Bean
    @SuppressWarnings("unchecked")
    public FilterRegistrationBean registrationAuthcFilterBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean(authFilter());
        //取消自动注册功能 Filter自动注册,不会添加到FilterChain中.
        registration.setEnabled(false);
        return registration;
    }

    private class AuthFilter extends AccessControlFilter {

        @Autowired
        private AuthApiService authApiService;

        @Override
        protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
            return false;
        }

        /**
         * 验证用户token
         */
        @Override
        protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
            String userToken = ((HttpServletRequest) request).getHeader(Constant.AUTHOR_PARAM);
            if (StringUtils.isNotEmpty(userToken)) {
                String sub = Tools.tokenInfo(userToken, Constant.JWT_SUB, String.class);
                // 只允许jelly登录用户访问
                if ("jelly".equals(sub)) {
                    Integer userId = Tools.tokenInfo(userToken, Constant.JWT_TOKEN_USERID, Integer.class);
                    String jti = Tools.tokenInfo(userToken, Constant.JWT_ID, String.class);
                    AuthCheckResult result = authApiService.checkToken(userId, jti);
                    if (result != null && result.isPass()) {
                        String userName = Tools.tokenInfo(userToken, Constant.JWT_TOKEN_USERNAME, String.class);
                        getSubject(request, response).login(new CalmAuthToken(userName, userId, userToken, result.getRoleSet(), result.getPermSet()));
                        return true;
                    }
                }
            }

            log.debug("userToken 验证失败 ：" + userToken);
            ResponseUtils.response(response, Result.error(StatusCode.TOKEN_FAULT));
            return false;
        }

    }

}

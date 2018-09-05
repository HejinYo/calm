package cn.hejinyo.calm.auth.config;

import cn.hejinyo.calm.common.basis.consts.Constant;
import cn.hejinyo.calm.common.basis.consts.StatusCode;
import cn.hejinyo.calm.common.basis.utils.Result;
import cn.hejinyo.calm.common.web.shiro.CalmAuthRealm;
import cn.hejinyo.calm.common.web.shiro.CalmShiroConfig;
import cn.hejinyo.calm.common.web.shiro.FilterAnonConfig;
import cn.hejinyo.calm.common.web.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.*;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2017/8/17 23:22
 */
@Configuration
@Slf4j
public class AuthShiroConfig extends CalmShiroConfig {

    private static final String AUTH_AUTH_NAME = "auth";

    /**
     * 自定义realms
     */
    @Override
    @Bean
    protected List<Realm> getRealms() {
        List<Realm> list = new ArrayList<>();
        list.add(calmAuthRealm());
        return list;
    }

    @Bean
    public CalmAuthRealm calmAuthRealm() {
        return new CalmAuthRealm();
    }

    /**
     * 注入自定义拦截器,注意拦截器自注入问题
     */
    @Override
    protected Map<String, Filter> getFilters() {
        Map<String, Filter> list = new HashMap<>();
        list.put(AUTH_AUTH_NAME, new AuthFilter());
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
            log.info("auth 不拦截路径 => {}", path);
            filterMap.put(path, "anon");
        });
        // 不拦截内部服务调用
        filterMap.put(Constant.SERVER_API_ANON, "anon");
        filterMap.put("/**", AUTH_AUTH_NAME);
        return filterMap;
    }

    @Bean
    public FilterAnonConfig filterAnonConfig() {
        return new FilterAnonConfig();
    }

    private class AuthFilter extends AccessControlFilter {

        @Override
        protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
            return false;
        }

        /**
         * 验证用户token
         */
        @Override
        protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
            log.debug("calm-auth其他服务不允许访问");
            ResponseUtils.response(response, HttpStatus.UNAUTHORIZED.value(), Result.error(StatusCode.USER_UNAUTHORIZED));
            return false;
        }

    }

}

package cn.hejinyo.calm.gateway.config;

import cn.hejinyo.calm.gateway.handler.AccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * https://projects.spring.io/spring-security-oauth/docs/oauth2.html
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/8/28 22:32
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.accessDeniedHandler(accessDeniedHandler);
    }

    /**
     * 要正常运行，需要反注释掉这段，具体原因见下面分析
     * 这里设置需要token验证的url
     * 这些需要在WebSecurityConfigurerAdapter中排查掉
     * 否则优先进入WebSecurityConfigurerAdapter,进行的是basic auth或表单认证,而不是token认证
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/res/**")
                .and()
                .authorizeRequests()
                .antMatchers("/res/open/**").permitAll()
                .antMatchers("/res/**").authenticated();
    }


}
package cn.hejinyo.calm.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * security基本配置
 * WebSecurityConfigurerAdapter的order是100
 * WebSecurityConfigurerAdapter的拦截要优先于ResourceServerConfigurerAdapter
 * WebSecurityConfigurerAdapter用于保护oauth相关的endpoints，同时主要作用于用户的登录(form login,Basic auth)
 * ResourceServerConfigurerAdapter用于保护oauth要开放的资源，同时主要作用于client端以及token的认证(Bearer auth)
 * https://segmentfault.com/a/1190000012260914
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/8/26 18:59
 */
@Configuration
@EnableWebSecurity
public class AuthSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authentication/form")
                //.successHandler(myAuthenticationSuccessHandler)
                //.failureHandler(myAuthenticationFailHander)
                //.defaultSuccessUrl("/welcome")
                //.failureUrl("/login-error")
                .permitAll()

                .and()

                .authorizeRequests()
                //不拦截oauth要开放的资源
                .antMatchers("/oauth/*").permitAll()
                //这就表示 /secure这个页面不需要权限认证，所有人都可以访问
                .antMatchers("/authentication/**").permitAll()
                .antMatchers("/**/css/**").permitAll()
                .antMatchers("/**/img/**").permitAll()
                // 这就表示/whoim的这个资源需要有ROLE_ADMIN的这个角色才能访问。不然就会提示拒绝访问
                /*.antMatchers("/whoim").hasRole("ADMIN1")*/
                /*// 适用于Restful风格的API
                .antMatchers(HttpMethod.POST, "/user/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/user/*").hasRole("USER")
                // 其余必须经过认证以后才能访问
                .anyRequest().authenticated()
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")*/
                .anyRequest().authenticated()

                .and()

                .csrf().disable();
    }

    /**
     * 需要配置这个支持password模式
     * support password grant type
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

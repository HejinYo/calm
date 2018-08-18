package cn.hejinyo.calm.customer.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * springboot 2.0
 * 需要添加 ServletRegistrationBean 因为springboot的默认路径不是 "/hystrix.stream"
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/8/18 15:41
 */
@Component
public class HystrixDashboardConfig {
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}

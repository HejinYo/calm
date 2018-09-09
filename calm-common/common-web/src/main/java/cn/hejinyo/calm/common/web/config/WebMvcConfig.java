package cn.hejinyo.calm.common.web.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * SpringMVC配置
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2017/8/16 23:38
 */
@Configuration
public class WebMvcConfig {

    @Bean
    public HttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //fast参数配置
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //是否需要格式化
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 自定义时间格式
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //禁用循环引用检测
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        //返回字符串格式
        List<MediaType> fastMediaTypes = new ArrayList<MediaType>() {{
            add(MediaType.APPLICATION_JSON_UTF8);
        }};
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return fastConverter;
    }


}
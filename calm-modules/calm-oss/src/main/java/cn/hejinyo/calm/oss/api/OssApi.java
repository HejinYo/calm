package cn.hejinyo.calm.oss.api;

import cn.hejinyo.calm.common.basis.consts.Constant;
import cn.hejinyo.calm.oss.factory.OSSFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/9/8 20:06
 */
@RestController
@RequestMapping(Constant.SERVER_API + "/config")
@Slf4j
public class OssApi {
    /**
     * 文件上传
     *
     * @param data 文件字节数组
     * @param path 文件路径，包含文件名
     * @return 返回http地址
     */
    public String upload(byte[] data, String path) {
        return OSSFactory.build().upload(data, path);
    }

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param path        文件路径，包含文件名
     * @return 返回http地址
     */
    public String upload(InputStream inputStream, String path) {
        return OSSFactory.build().upload(inputStream, path);
    }

    /**
     * 删除文件
     */
    public void delete(String key) {
        OSSFactory.build().delete(key);
    }
}

package cn.hejinyo.calm.oss.service;

import cn.hejinyo.calm.oss.model.CloudStorageDTO;
import cn.hejinyo.calm.oss.model.OssFileInfo;

import java.io.InputStream;
import java.util.List;

/**
 * 云存储
 *
 * @author HejinYo hejinyo@gmail.com
 * @date 2017/9/26 11:00
 */
public abstract class CloudStorageService {
    /**
     * 云存储配置信息
     */
    public CloudStorageDTO config;

    /**
     * 文件上传
     *
     * @param data 文件字节数组
     * @param path 文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(byte[] data, String path);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param path        文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(InputStream inputStream, String path);

    /**
     * 删除文件
     */
    public abstract void delete(String key);

    /**
     * 查询获取文件列表。
     */
    public abstract List<OssFileInfo> list(String key, int limit);
}

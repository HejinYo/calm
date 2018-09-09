package cn.hejinyo.calm.wecaht.service.impl;

import cn.hejinyo.calm.wecaht.model.JokeEntity;
import cn.hejinyo.calm.wecaht.repository.JokeRepository;
import cn.hejinyo.calm.wecaht.service.WechatJokeService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2017/8/23 22:23
 * @Description :
 */
@Service
public class WechatJokeServiceImpl implements WechatJokeService {

    @Autowired
    private JokeRepository jokeRepository;

    @Override
    public JokeEntity getRandom() {
        Random rand = new Random();
        int randNum = 9000;
        int count = 0;
        JokeEntity JokeEntity = jokeRepository.findById(rand.nextInt(randNum)).orElse(null);
        while (null == JokeEntity && count < 10) {
            JokeEntity = jokeRepository.findById(rand.nextInt(randNum)).orElse(null);
            count++;
        }
        return JokeEntity;
    }

    @Override
    public String weater(String citys) {
        try {
            //参数url化
            String city = java.net.URLEncoder.encode(citys, "utf-8");

            //拼地址
            String apiUrl = String.format("http://www.sojson.com/open/api/weather/json.shtml?city=%s", city);
            //开始请求
            URL url = new URL(apiUrl);
            URLConnection open = url.openConnection();
            InputStream input = open.getInputStream();
            //这里转换为String，带上包名，怕你们引错包
            String result = IOUtils.toString(input, "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONObject yesterday = jsonObject.getJSONObject("data").getJSONObject("yesterday");
            String date = yesterday.getString("date");
            String high = yesterday.getString("high");
            String low = yesterday.getString("low");
            String fx = yesterday.getString("fx");
            String fl = yesterday.getString("fl");
            String type = yesterday.getString("type");
            String notice = yesterday.getString("notice");
            String tq = "今天是" + date + "," + citys + "今天是" + type + ";" + high + "," + low + "," + type + ",温馨提醒:" + notice;
            return tq;
        } catch (Exception e) {
            return "死鬼，真调皮，地名输入有误哦！";
            //e.printStackTrace();
        }
    }
}

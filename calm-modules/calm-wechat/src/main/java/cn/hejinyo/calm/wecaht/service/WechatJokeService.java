package cn.hejinyo.calm.wecaht.service;

import cn.hejinyo.calm.wecaht.model.JokeEntity;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2017/8/23 22:23
 */
public interface WechatJokeService {

    JokeEntity getRandom();

    String weater(String citys);
}

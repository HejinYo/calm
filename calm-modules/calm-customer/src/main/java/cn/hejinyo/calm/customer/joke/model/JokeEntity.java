package cn.hejinyo.calm.customer.joke.model;

import lombok.Data;

import java.util.Date;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/7/19 23:14
 */
@Data
public class JokeEntity {

    private Integer id;

    private String title;

    private String content;

    private Integer typid;

    private Integer hits;

    private Date createTime;
}

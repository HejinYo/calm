package cn.hejinyo.calm.provider.joke.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/7/19 23:14
 */
@Data
@Entity
@Table(name = "wechat_joke")
public class JokeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    private String content;

    private Integer typid;

    private Integer hits;

    @Column(name = "dateandtime")
    private Date createTime;
}

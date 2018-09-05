package cn.hejinyo.calm.common.web.shiro;

import lombok.Data;

import java.util.Set;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/9/2 18:07
 */
@Data
public class CalmUserDetails {
    private String userName;
    private Integer userId;
    private String jwt;
    private Set<String> roleSet;
    private Set<String> permSet;

    public CalmUserDetails() {

    }

    public CalmUserDetails(String userName, Integer userId, String jwt, Set<String> roleSet, Set<String> permSet) {
        this.userName = userName;
        this.userId = userId;
        this.jwt = jwt;
        this.roleSet = roleSet;
        this.permSet = permSet;
    }
}

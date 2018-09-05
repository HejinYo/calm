package cn.hejinyo.calm.common.web.shiro;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.authc.AuthenticationToken;

import java.util.Set;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/9/2 15:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CalmAuthToken extends CalmUserDetails implements AuthenticationToken {

    public CalmAuthToken(String userName, Integer userId, String jwt, Set<String> roleSet, Set<String> permSet) {
        super.setUserName(userName);
        super.setUserId(userId);
        super.setJwt(jwt);
        super.setRoleSet(roleSet);
        super.setPermSet(permSet);
    }

    @Override
    public Object getPrincipal() {
        return getUserName();
    }

    @Override
    public Object getCredentials() {
        return getJwt();
    }
}

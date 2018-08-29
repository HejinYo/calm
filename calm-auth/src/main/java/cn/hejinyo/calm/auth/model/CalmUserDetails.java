package cn.hejinyo.calm.auth.model;

import cn.hejinyo.calm.common.consts.Constant;
import cn.hejinyo.calm.common.model.dto.SysUserDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/8/29 22:28
 */
@Data
public class CalmUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    /**
     * 用户编号 user_id
     **/
    private Integer userId;

    /**
     * 用户昵称 nick_name
     **/
    private String nickName;

    /**
     * 用户名 user_name
     **/
    private String userName;

    /**
     * 用户密码 user_pwd
     **/
    private String userPwd;

    /**
     * 用户状态 0：正常；1：禁用； state
     **/
    private Integer state;

    /**
     * 拥有的角色编码列表
     */
    private Set<String> roleCodeSet;

    public CalmUserDetails(SysUserDTO sysUserDTO) {
        this.userId = sysUserDTO.getUserId();
        this.nickName = sysUserDTO.getNickName();
        this.userName = sysUserDTO.getUserName();
        this.userPwd = sysUserDTO.getUserPwd();
        this.state = sysUserDTO.getState();
        this.roleCodeSet = sysUserDTO.getRoleCodeSet();
    }

    public CalmUserDetails() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorityList = new HashSet<>();
        for (String roleCode : roleCodeSet) {
            authorityList.add(new SimpleGrantedAuthority(roleCode));
        }
        // 基本用户角色
        authorityList.add(new SimpleGrantedAuthority(Constant.AuthoritiesEnum.USER.getRole()));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.userPwd;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return Constant.Status.DISABLE.equals(this.state);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Constant.Status.NORMAL.equals(this.state);
    }
}

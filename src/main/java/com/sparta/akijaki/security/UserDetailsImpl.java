package com.sparta.akijaki.security;


import com.sparta.akijaki.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;

@Getter

public class UserDetailsImpl implements UserDetails {
    private final User user;


    public UserDetailsImpl(User user) {
        this.user = user;
    }
    //질문!!!!!!!!!!!!!!!!!!!
    public User getUser(){
        return user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        UserRoleEnum role = user.getRole();
//        String authority = role.getAuthority();
//
//        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(simpleGrantedAuthority);
//
        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

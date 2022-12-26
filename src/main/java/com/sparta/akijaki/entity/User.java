package com.sparta.akijaki.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,  unique = true, columnDefinition = "VARCHAR(10)")
    private String username;


    @Column(nullable = false)
    private String password;

//   @Column(nullable = false)
//    private String nickname;

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(nullable = false)
    private boolean userStatus;


    public User(String username, String password,  UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
        userStatus = true;
    }

    public void setUserStatus(boolean userStatus){
        this.userStatus = userStatus;
    }
}
package com.sparta.akijaki.entity;

public enum UserRoleEnum {
    //일반 사용자 권한
    USER(Authority.USER),

    //관리자 권한
    ADMIN(Authority.ADMIN);

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}

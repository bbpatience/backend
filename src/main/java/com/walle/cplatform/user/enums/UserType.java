package com.walle.cplatform.user.enums;

public enum UserType {
    ADMIN(0),

    TEACHER(1),

    CUSTOMER(2);

    private int type;

    UserType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}

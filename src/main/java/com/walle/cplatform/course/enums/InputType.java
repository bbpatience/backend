package com.walle.cplatform.course.enums;

public enum InputType {
    CUSTOMER(0),
    CLASS(1),
    TEACHER(2);

    private int type;
    InputType(int type) {
        this.type = type;
    }
    public int getType() {
        return this.type;
    }
}

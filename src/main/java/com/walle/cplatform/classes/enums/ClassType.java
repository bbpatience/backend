package com.walle.cplatform.classes.enums;

public enum ClassType {
    MASTER(0),
    SLAVE(1),;

    private int type;
    ClassType(int type) {
        this.type = type;
    }
    public int getType() {
        return this.type;
    }
}

package com.walle.cplatform.classes.enums;

public enum ClassState {
    NORMAL(0),
    DELETED(1),;

    private int state;

    ClassState(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }
}

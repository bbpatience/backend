package com.walle.cplatform.customer.enums;

public enum CardType {
    CHUANGEZHI(0),

    CHUANGECAI(1),

    NIANDI(2),

    BINGO(3);

    private int type;

    CardType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}

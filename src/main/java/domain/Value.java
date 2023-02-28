package domain;

import java.nio.channels.NetworkChannel;

public enum Value {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    ACE("A", 11),
    KING("K", 10),
    QUEEN("Q", 10),
    JACK("J", 10);

    final String expression;
    final int value;

    Value(String expression, int value) {
        this.expression = expression;
        this.value = value;
    }

    public String getExpression() {
        return expression;
    }

    public int getValue() {
        return value;
    }

}

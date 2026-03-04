package domain;

import java.util.Arrays;

public enum CardShape {
    HEART(1, "HEART"),
    SPADE(2, "SPADE"),
    DIAMOND(3, "DIAMOND"),
    CLUB(4, "CLUB");

    private int code;
    private String name;

    CardShape(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CardShape getShape(int code) {
        return Arrays.stream(values())
                .filter(val -> code == val.code)
                .findFirst()
                .orElse(null);
    }

}

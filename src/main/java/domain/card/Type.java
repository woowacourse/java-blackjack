package domain.card;

import java.util.Arrays;

public enum Type {
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private final String name;

    Type(final String name) {
        this.name = name;
    }

    public static Type of(final String name) {
        return Arrays.stream(values())
                .filter(type -> type.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다."));
    }

    public String getName() {
        return name;
    }
}

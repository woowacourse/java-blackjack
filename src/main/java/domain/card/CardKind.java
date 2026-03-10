package domain.card;

import java.util.Arrays;

public enum CardKind {
    CLOVER("클로버"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    SPADES("스페이드");

    private final String kind;

    CardKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public static CardKind of(final String kind) {
        return Arrays.stream(values())
                .filter(val -> val.kind.equals(kind))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("생성할 수 없는 kind 입니다."));
    }
}

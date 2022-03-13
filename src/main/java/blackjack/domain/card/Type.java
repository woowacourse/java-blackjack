package blackjack.domain.card;

import java.util.List;

public enum Type {

    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    HEART("하트"),
    ;

    private final String name;

    Type(String name) {
        this.name = name;
    }

    public static List<Type> getTypeValues() {
        return List.of(values());
    }

    public String getName() {
        return name;
    }
}

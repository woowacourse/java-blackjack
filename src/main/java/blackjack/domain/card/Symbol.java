package blackjack.domain.card;

import java.util.Arrays;

public enum Symbol {
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private static final String WRONG_SYMBOL_EXCEPTION_MESSAGE = "입력된 문양은 없는 카드문양입니다! : %s";

    private final String name;

    Symbol(final String name) {
        this.name = name;
    }

    public static Symbol from(final String name) {
        return Arrays.stream(values())
                .filter(symbol -> symbol.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format(WRONG_SYMBOL_EXCEPTION_MESSAGE, name)));
    }

    public String getName() {
        return name;
    }
}

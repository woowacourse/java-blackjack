package blackjack.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum Symbol {
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private static final Map<String, Symbol> SYMBOLS = new HashMap<>();

    static {
        for (Symbol symbol : values()) {
            SYMBOLS.put(symbol.name, symbol);
        }
    }

    private final String name;

    Symbol(String name) {
        this.name = name;
    }

    public static Symbol from(String name) {
        Symbol symbol = SYMBOLS.get(name);
        if (Objects.isNull(symbol)) {
            throw new IllegalArgumentException(String.format("입력된 문양은 없는 카드문양입니다! : %s", name));
        }
        return symbol;
    }
}

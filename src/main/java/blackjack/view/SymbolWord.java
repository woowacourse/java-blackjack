package blackjack.view;

import blackjack.domain.Symbol;
import java.util.Arrays;

public enum SymbolWord {

    SPADE(Symbol.SPADE, "스페이드"),
    HEART(Symbol.HEART, "하트"),
    DIAMOND(Symbol.DIAMOND, "다이아몬드"),
    CLUB(Symbol.CLUB, "클로버");

    private final Symbol symbol;
    private final String word;

    SymbolWord(Symbol symbol, String word) {
        this.symbol = symbol;
        this.word = word;
    }

    public static String toWord(Symbol findSymbol) {
        SymbolWord symbolWord = Arrays.stream(values())
                .filter(symbol -> symbol.symbol == findSymbol)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 심볼이 존재하지 않습니다."));
        return symbolWord.word;
    }
}

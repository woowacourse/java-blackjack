package blackjack.view;

import blackjack.domain.Symbol;

import java.util.Arrays;

public enum OutputViewSymbol {
    ACE("A", Symbol.ACE),
    TWO("2", Symbol.TWO),
    THREE("3", Symbol.THREE),
    FOUR("4", Symbol.FOUR),
    FIVE("5", Symbol.FIVE),
    SIX("6", Symbol.SIX),
    SEVEN("7", Symbol.SEVEN),
    EIGHT("8", Symbol.EIGHT),
    NINE("9", Symbol.NINE),
    TEN("10", Symbol.TEN),
    JACK("J", Symbol.JACK),
    QUEEN("Q", Symbol.QUEEN),
    KING("K", Symbol.KING);

    private final String printSymbol;
    private final Symbol symbol;

    OutputViewSymbol(final String printSymbol, final Symbol symbol) {
        this.printSymbol = printSymbol;
        this.symbol = symbol;
    }

    public static OutputViewSymbol from(final Symbol symbol) {
        return Arrays.stream(values())
                .filter(it -> it.symbol == symbol)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 심볼입니다"));
    }

    public String getPrintSymbol() {
        return printSymbol;
    }
}

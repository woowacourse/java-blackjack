package blackjack.view;

import blackjack.domain.card.Denomination;

import java.util.Arrays;

public enum OutputViewSymbol {

    ACE("A", Denomination.ACE),
    TWO("2", Denomination.TWO),
    THREE("3", Denomination.THREE),
    FOUR("4", Denomination.FOUR),
    FIVE("5", Denomination.FIVE),
    SIX("6", Denomination.SIX),
    SEVEN("7", Denomination.SEVEN),
    EIGHT("8", Denomination.EIGHT),
    NINE("9", Denomination.NINE),
    TEN("10", Denomination.TEN),
    JACK("J", Denomination.JACK),
    QUEEN("Q", Denomination.QUEEN),
    KING("K", Denomination.KING);

    private final String printSymbol;
    private final Denomination symbol;

    OutputViewSymbol(String printSymbol, Denomination symbol) {
        this.printSymbol = printSymbol;
        this.symbol = symbol;
    }

    public static OutputViewSymbol from(Denomination symbol) {
        return Arrays.stream(values())
                .filter(it -> it.symbol == symbol)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 심볼입니다"));
    }

    public String getPrintSymbol() {
        return printSymbol;
    }

}

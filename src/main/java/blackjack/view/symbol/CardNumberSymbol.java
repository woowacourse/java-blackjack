package blackjack.view.symbol;

import blackjack.model.cards.CardNumber;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum CardNumberSymbol {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String symbol;

    CardNumberSymbol(String symbol) {
        this.symbol = symbol;
    }

    public static String convertToSymbol(CardNumber cardNumber) {
        return Arrays.stream(values())
                .filter(numberSymbol -> numberSymbol.name().equals(cardNumber.name()))
                .findFirst()
                .map(CardNumberSymbol::getSymbol)
                .orElseThrow(() -> new NoSuchElementException("올바르지 않은 카드 숫자입니다."));
    }

    public String getSymbol() {
        return symbol;
    }
}

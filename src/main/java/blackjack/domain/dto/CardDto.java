package blackjack.domain.dto;

import blackjack.domain.card.Card;

public class CardDto {
    private final String symbol;
    private final String denomination;

    public CardDto(String symbol, String denomination) {
        this.symbol = symbol;
        this.denomination = denomination;
    }

    public static CardDto of(Card card) {
        return new CardDto(card.getSymbolName(), card.getDenominationName());
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDenomination() {
        return denomination;
    }
}

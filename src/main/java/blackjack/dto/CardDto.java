package blackjack.dto;

import blackjack.domain.card.Card;

public class CardDto {

    private final String numberName;
    private final String symbolName;

    private CardDto(final String numberName, final String symbolName) {
        this.numberName = numberName;
        this.symbolName = symbolName;
    }

    public static CardDto from(final Card card) {
        return new CardDto(card.getNumberName(), card.getSymbolName());
    }

    public String getNumberName() {
        return numberName;
    }

    public String getSymbolName() {
        return symbolName;
    }
}

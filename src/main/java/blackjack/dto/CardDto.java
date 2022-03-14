package blackjack.dto;

import blackjack.model.card.Card;

public class CardDto {
    private final String number;
    private final String symbol;

    public CardDto(Card card) {
        this.number = card.getNumberOfString();
        this.symbol = card.getSymbol();
    }

    public String getCard() {
        return number + symbol;
    }
}

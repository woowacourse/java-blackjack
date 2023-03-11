package blackjack.domain.participant.dto;

import blackjack.domain.card.Card;

public class CardResponse {

    private final String symbol;
    private final String shape;

    private CardResponse(final String symbol, final String shape) {
        this.symbol = symbol;
        this.shape = shape;
    }

    public static CardResponse from(final Card card) {
        return new CardResponse(card.getSymbol().getName(), card.getShape().getName());
    }

    public String getSymbol() {
        return symbol;
    }

    public String getShape() {
        return shape;
    }
}

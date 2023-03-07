package blackjack.response;

import blackjack.domain.card.Card;

public class CardResponse {

    private final String symbol;
    private final String shape;

    private CardResponse(final String symbol, final String shape) {
        this.symbol = symbol;
        this.shape = shape;
    }

    public static CardResponse from(final Card card) {
        return new CardResponse(card.getSymbol().name(), card.getShape().name());
    }

    public String getSymbol(final CardConvertStrategy cardConvertStrategy) {
        return cardConvertStrategy.convertSymbol(symbol);
    }

    public String getShape(final CardConvertStrategy cardConvertStrategy) {
        return cardConvertStrategy.convertShape(shape);
    }
}

package blackjack.response;

import blackjack.domain.card.Card;

/**
 * 대부분의 Response 가 이 클래스를 통해서 symbol 과 shape 를 변경하는 구조로 되어있는데
 */
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

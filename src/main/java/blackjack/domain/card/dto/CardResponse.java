package blackjack.domain.card.dto;

import blackjack.domain.card.Card;
import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CardResponse that = (CardResponse) o;
        return Objects.equals(symbol, that.symbol) && Objects.equals(shape, that.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, shape);
    }

    @Override
    public String toString() {
        return "CardResponse{" +
                "symbol='" + symbol + '\'' +
                ", shape='" + shape + '\'' +
                '}';
    }

    public String getSymbol() {
        return symbol;
    }

    public String getShape() {
        return shape;
    }
}

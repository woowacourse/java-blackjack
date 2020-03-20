package blackjack.domain.card;

import blackjack.domain.card.component.Figure;
import blackjack.domain.card.component.Type;

import java.util.Objects;

public class Card {
    public static final String NULL_ERR_MSG = "인자에 Null 이 들어올 수 없습니다.";

    private Type type;
    private Figure figure;

    private Card(Type type, Figure figure) {
        this.type = type;
        this.figure = figure;
    }

    public static Card of(Type type, Figure figure) {
        Objects.requireNonNull(type, NULL_ERR_MSG);
        Objects.requireNonNull(figure, NULL_ERR_MSG);

        return new Card(type, figure);
    }

    public boolean isAce() {
        return this.type == Type.ACE;
    }

    public int cardValue() {
        return type.getValue();
    }

    public String cardInfo() {
        return type.getType() + figure.getFigure();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return type == card.type &&
                figure == card.figure;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, figure);
    }
}

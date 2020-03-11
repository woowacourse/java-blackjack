package blackjack.domain.card;

import java.util.Objects;

public class Card {
    private static final String NULL_ERROR_MSG = "생성자에 Null이 들어올 수 없습니다.";

    private Type type;
    private Figure figure;

    Card(Type type, Figure figure) {
        Objects.requireNonNull(type, NULL_ERROR_MSG);
        Objects.requireNonNull(figure, NULL_ERROR_MSG);

        this.type = type;
        this.figure = figure;
    }

    public boolean isAce() {
        return this.type == Type.ACE;
    }

    public int getCardValue() {
        return type.getValue();
    }

    public String showCardInfo() {
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

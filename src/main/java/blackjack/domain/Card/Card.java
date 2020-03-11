package blackjack.domain.Card;

import java.util.Objects;

public class Card {
    private static final String NULL_ERROR_MSG = "생성자에 Null이 들어올 수 없습니다.";
    private CardNumber number;
    private Figure figure;

    public Card(CardNumber number, Figure figure) {
        Objects.requireNonNull(number, NULL_ERROR_MSG);
        Objects.requireNonNull(figure, NULL_ERROR_MSG);

        this.number = number;
        this.figure = figure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number &&
                figure == card.figure;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, figure);
    }

    public boolean has(CardNumber number, Figure figure) {
        return this.number == number && this.figure == figure;
    }

    public Figure getFigure() { return figure; }

    public int getNumber() {
        return number.getNumber();
    }

    public String getInfo() {
        return number.getMessage() + getFigure().getFigure();
    }
    public boolean has(CardNumber number) {
        return this.number == number;
    }
}

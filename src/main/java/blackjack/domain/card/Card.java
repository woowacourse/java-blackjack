package blackjack.domain.card;

import java.util.Objects;

public class Card {
    private static final String NULL_ERROR_MSG = "생성자에 Null이 들어올 수 없습니다.";
    private CardNumber number;
    private CardFigure cardFigure;

    public Card(CardNumber number, CardFigure cardFigure) {
        Objects.requireNonNull(number, NULL_ERROR_MSG);
        Objects.requireNonNull(cardFigure, NULL_ERROR_MSG);

        this.number = number;
        this.cardFigure = cardFigure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number &&
                cardFigure == card.cardFigure;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, cardFigure);
    }

    public boolean has(CardNumber number, CardFigure cardFigure) {
        return this.number == number && this.cardFigure == cardFigure;
    }

    public CardFigure getCardFigure() { return cardFigure; }

    public int getNumber() {
        return number.getNumber();
    }

    public String getInfo() {
        return number.getMessage() + getCardFigure().getFigure();
    }

    public boolean has(CardNumber number) {
        return this.number == number;
    }
}

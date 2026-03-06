package blackjack.model;

import java.util.Objects;

public class Card {
    private final Figure figure; // 모양, 스페이드, 하트 등등
    private final Number number; // a->k

    public Card(Figure figure, Number number) {
        this.figure = figure;
        this.number = number;
    }

    public String getCardName() {
        return number.getName() + figure.getName();
    }

    public int getNumberValue() {
        return number.getValue();
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return figure == card.figure && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(figure, number);
    }
}

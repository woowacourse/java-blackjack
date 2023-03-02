package blackjack.domain;

import java.util.Objects;

public class Card {

    private final CardShape cardShape;
    private final CardNumber number;

    public Card(final CardShape cardShape, final CardNumber number) {
        this.cardShape = cardShape;
        this.number = number;
    }

    public CardShape getShape(){
        return cardShape;
    }

    public CardNumber getNumber(){
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardShape == card.cardShape && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardShape, number);
    }
}

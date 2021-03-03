package blackjack.domain.card;

import java.util.Objects;

public class Card implements Comparable<Card> {
    private final Shape shape;
    private final Denomination denomination;

    public Card(Shape shape, Denomination denomination) {
        this.shape = shape;
        this.denomination = denomination;
    }

    public Denomination getDenomination() {
        return this.denomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        if (!Objects.equals(shape, card.shape)) return false;
        return Objects.equals(denomination, card.denomination);
    }

    @Override
    public int hashCode() {
        int result = shape != null ? shape.hashCode() : 0;
        result = 31 * result + (denomination != null ? denomination.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Card card) {
        return card.denomination.getScore() - this.denomination.getScore();
    }
    /**/
    public boolean isAce() {
        return this.denomination.equals(Denomination.ACE);
    }
}

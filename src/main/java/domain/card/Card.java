package domain.card;


import java.util.Objects;

public class Card {

    private final Suit suit;
    private final Denomination denomination;

    private Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static Card of(Suit suit, Denomination denomination) {
        return new Card(suit, denomination);
    }

    public Suit getSuit() {
        return suit;
    }

    public Denomination getNumber() {
        return denomination;
    }

    public boolean is(Denomination denomination) {
        return denomination.equals(this.denomination);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return suit == card.suit && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }
}

package domain.card;

import java.util.Objects;

public class Card {
    private static final String ACE_SYMBOL = "A";

    private final Suit suit;
    private final Denomination denomination;

    private Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static Card of(Suit suit, Denomination denomination) {
        return new Card(suit, denomination);
    }

    public String getCardName() {
        if (isAce()) {
            return ACE_SYMBOL + this.suit.getName();
        }

        return this.denomination.getScore() + this.suit.getName();
    }

    public boolean isAce() {
        return denomination.isAce();
    }

    public int getScore() {
        return denomination.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card that = (Card) o;
        return suit == that.suit && denomination == that.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Card{");
        sb.append("suit=").append(suit);
        sb.append(", denomination=").append(denomination);
        sb.append('}');
        return sb.toString();
    }
}

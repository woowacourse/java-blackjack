package domain.card;

import java.util.Objects;

public class PlayingCard {
    private static final String ACE_NUMBERS = "(1,11)";

    private final Suit suit;
    private final Denomination denomination;

    private PlayingCard(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static PlayingCard of(Suit suit, Denomination denomination) {
        return new PlayingCard(suit, denomination);
    }

    public String getCardName() {
        if (isAce()) {
            return ACE_NUMBERS + this.suit.getName();
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
        PlayingCard that = (PlayingCard) o;
        return suit == that.suit && denomination == that.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }

    @Override
    public String toString() {
        return "PlayingCard{" +
                "suit=" + suit +
                ", denomination=" + denomination +
                '}';
    }
}

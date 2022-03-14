package blackjack.domain.card;

public class PlayingCard {

    private final Suit suit;
    private final Denomination denomination;

    public PlayingCard(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public boolean isAce() {
        return denomination.equals(Denomination.ACE);
    }

    public int sumPoint(int points) {
        return points + denomination.getPoint();
    }

    public Suit getSuit() {
        return suit;
    }

    public Denomination getRank() {
        return denomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayingCard playingCard = (PlayingCard) o;

        if (suit != playingCard.suit) return false;
        return denomination == playingCard.denomination;
    }

    @Override
    public int hashCode() {
        int result = suit != null ? suit.hashCode() : 0;
        result = 31 * result + (denomination != null ? denomination.hashCode() : 0);
        return result;
    }
}

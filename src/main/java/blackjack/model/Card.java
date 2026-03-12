package blackjack.model;

public record Card(
    Rank rank,
    Suit suit
) {
    public int getValue() {
        return rank.getValue();
    }

    public boolean isAce() {
        return this.rank == Rank.ACE;
    }
}

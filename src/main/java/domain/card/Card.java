package domain.card;

public record Card(Suit suit, Rank rank) {
    public boolean isAce() {
        return this.rank == Rank.ACE;
    }
}

package blackjack.domain.card;

public record Card(Rank rank, Suit suit) {

    public boolean isAce() {
        return this.rank == Rank.ACE;
    }
}

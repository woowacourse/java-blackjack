package domain.card;

public abstract class TrumpCard {
    private final Rank rank;
    private final Suit suit;

    public TrumpCard(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }
}

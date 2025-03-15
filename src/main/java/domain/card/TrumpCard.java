package domain.card;

public abstract class TrumpCard {

    protected final Rank rank;
    protected final Suit suit;

    protected TrumpCard(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }
}

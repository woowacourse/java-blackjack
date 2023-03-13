package blackjack.domain.card;

public final class Card {
    private final Rank rank;
    private final Shape shape;

    public Card(final Rank rank, final Shape shape) {
        this.rank = rank;
        this.shape = shape;
    }

    public boolean isAce() {
        return rank.isAce();
    }

    public int score() {
        return rank.score();
    }

    @Override
    public String toString() {
        return "Card{" +
                "rank=" + rank +
                ", shape=" + shape +
                '}';
    }

    public String getSymbol() {
        return rank.getSymbol() + shape.getSymbol();
    }
}

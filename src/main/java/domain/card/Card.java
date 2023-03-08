package domain.card;

public class Card {
    private final CardShape shape;
    private final CardRank rank;

    private Card(CardShape shape, CardRank rank) {
        this.shape = shape;
        this.rank = rank;
    }

    public static Card of(CardShape shape, CardRank rank) {
        return new Card(shape, rank);
    }

    public boolean isAce() {
        return this.rank == CardRank.ACE;
    }

    public int getScore() {
        return rank.getScore();
    }

    public String getCardShapeName() {
        return shape.getName();
    }

    public String getCardRankName() {
        return rank.getName();
    }
}

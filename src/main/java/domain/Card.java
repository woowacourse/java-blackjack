package domain;

public class Card {
    private final CardShape shape;
    private final CardRank rank;

    public Card(CardShape shape, CardRank number) {
        this.shape = shape;
        this.rank = number;
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

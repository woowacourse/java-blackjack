package model.card;

public class SingleScoreCard extends Card {

    public SingleScoreCard(Suit suit, Rank rank) {
        super(suit, rank);
    }

    @Override
    public int findAdjustOrDefalutScore() {
        return rank.getDefaultScore();
    }
}

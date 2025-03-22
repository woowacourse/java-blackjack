package model.card;

public class MultiScoreCard extends Card {

    private int scoreIdx;

    public MultiScoreCard(Suit suit, Rank rank) {
        super(suit, rank);
        scoreIdx = 0;
    }

    @Override
    public void adjustOrDefaultScore() {
        scoreIdx = rank.findAdjustOrOriginalIdx(scoreIdx);
    }

    @Override
    public int getScore() {
        return rank.findScore(scoreIdx);
    }
}

package model.card;

public class MultiScoreCard extends Card {

    private int scoreIdx;

    public MultiScoreCard(Suit suit, Rank rank) {
        super(suit, rank);
        scoreIdx = 0;
    }

    @Override
    public int findAdjustOrDefalutScore() {
        scoreIdx = rank.findNextScore(scoreIdx);
        return rank.findScore(scoreIdx);
    }

    @Override
    public int getScore() {
        return rank.findScore(scoreIdx);
    }
}

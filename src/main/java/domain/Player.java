package domain;

public class Player extends Participant {
    public Player(final String name) {
        super(name);
    }

    public int calculateScoreWhileDraw() {
        return hand.calculateScoreWhileDraw();
    }

    public boolean isNotSameScoreAs(final Player other) {
        return calculateResultScore() != other.calculateResultScore();
    }

    public boolean hasMoreScoreThan(final int otherScore) {
        return calculateResultScore() > otherScore;
    }

    public boolean hasMoreScoreThan(final Player other) {
        return calculateResultScore() > other.calculateResultScore();
    }

    public int calculateResultScore() {
        return hand.calculateScore(BLACKJACK_SCORE);
    }

    public boolean hasLessOrSameCardThan(final Player other) {
        return getTotalSize() <= other.getTotalSize();
    }

    public boolean cannotDraw() {
        return hand.calculateScore(BLACKJACK_SCORE) > BLACKJACK_SCORE;
    }

    public int getTotalSize() {
        return hand.size();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

}

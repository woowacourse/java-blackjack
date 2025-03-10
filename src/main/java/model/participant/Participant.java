package model.participant;

import model.card.Card;
import model.score.Score;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    private final List<Card> hands;
    protected Score score;

    protected Participant() {
        this.hands = new ArrayList<>();
        this.score = Score.newInstance();
    }

    public void addCards(final List<Card> findCards) {
        hands.addAll(findCards);
        updateScore(findCards);
        adjustScoreIfNeeded();
    }

    private void updateScore(List<Card> findCards) {
        int additionalScore = sumTo(findCards);
        addScore(additionalScore);
    }

    private int sumTo(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getRankScore)
                .sum();
    }

    private void adjustScoreIfNeeded() {
        if (!isBust()) {
            return ;
        }
        for (Card card : hands) {
            int oldRankScore = card.getRankScore();
            int adjustedRankScore = card.adjustRank();
            if (adjustedRankScore != -1) {
                updateScore(oldRankScore, adjustedRankScore);
                if (!isBust()) {
                    return ;
                }
            }
        }
    }

    private void updateScore(int oldValue, int newValue) {
        subtractScore(oldValue);
        addScore(newValue);
    }

    public boolean canHit() {
        return score.canHit();
    }

    public boolean isBust() {
        return score.isBust();
    }

    public void subtractScore(int value) {
        score = score.minus(value);
    }

    public void addScore(int value) {
        score = score.plus(value);
    }

    public int getSum() {
        return score.getValue();
    }

    abstract public String getNickname();

    public List<Card> getHands() {
        return hands;
    }
}

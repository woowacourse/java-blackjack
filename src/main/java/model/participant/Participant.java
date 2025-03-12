package model.participant;

import model.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    private final List<Card> hands;
    // protected Score score;

    protected Participant() {
        this.hands = new ArrayList<>();
    }

    public void addCards(final List<Card> findCards) {
        hands.addAll(findCards);
        adjustScoreIfNeeded();
    }

    private void adjustScoreIfNeeded() {
        int score = getScore();
        if (!isBust(score)) {
            return ;
        }
        for (Card card : hands) {
            int oldRankScore = card.getRankScore();
            int adjustedRankScore = card.adjustRank();
            if (adjustedRankScore != -1) {
                score -= oldRankScore;
                score += adjustedRankScore;
                if (!isBust(score)) {
                    return ;
                }
            }
        }
    }

    public boolean canHit() {
        return getScore() < 21;
    }

    public boolean isBust(int score) {
        return score > 21;
    }

    abstract public String getNickname();

    public List<Card> getHands() {
        return hands;
    }

    public int getScore() {
        return hands.stream()
                .mapToInt(Card::getRankScore)
                .sum();
    }
}

package model.participant;

import model.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    private final List<Card> hands;

    protected Participant() {
        this.hands = new ArrayList<>();
    }

    public void addCards(final List<Card> findCards) {
        hands.addAll(findCards);
        adjustScoreIfNeeded();
    }

    private void adjustScoreIfNeeded() {
        if (!isBust()) {
            return ;
        }
        for (Card card : hands) {
            int adjustedRankScore = card.adjustRank();
            if (adjustedRankScore != -1) {
                if (!isBust()) {
                    return ;
                }
            }
        }
    }

    public boolean canHit() {
        return getScore() < 21;
    }

    public boolean isBust() {
        return getScore() > 21;
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

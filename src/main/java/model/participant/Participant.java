package model.participant;

import model.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    public static final int BLACK_JACK_SCORE = 21;
    private final List<Card> hands;

    protected Participant() {
        this.hands = new ArrayList<>();
    }

    public void addCards(final List<Card> findCards) {
        hands.addAll(findCards);
        adjustScoreIfNeeded();
    }

    private void adjustScoreIfNeeded() {
        if (canHit() || isBlackjack()) {
            return;
        }
        for (Card card : hands) {
            card.findAdjustOrDefaultScore();
            if (canHit() || isBlackjack()) {
                return;
            }
        }
    }

    public boolean canHit() {
        return getScore() < BLACK_JACK_SCORE;
    }

    public boolean isBust() {
        return getScore() > BLACK_JACK_SCORE;
    }

    public boolean isBlackjack() {
        return getScore() == BLACK_JACK_SCORE;
    }

    abstract public String getNickname();

    public List<Card> getHands() {
        return hands;
    }

    public int getScore() {
        return hands.stream()
                .mapToInt(Card::getScore)
                .sum();
    }
}

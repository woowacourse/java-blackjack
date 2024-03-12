package domain.player;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Rank;
import java.util.Collections;
import java.util.List;

public abstract class Participant {
    private static final int ACE_HIGH = 11;
    static final int PERFECT_SCORE = 21;

    private final Cards hands;

    public Participant() {
        this.hands = Cards.makeEmptyDeck();
    }

    public boolean isBust() {
        return calculateScore() > PERFECT_SCORE;
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public abstract boolean canHit();

    public abstract boolean canNotHit();

    public String getName() {
        throw new IllegalCallerException("참여자의 이름이 정해지지 않았습니다");
    }

    public void hit(final Card card) {
        hands.add(card);
    }

    public int calculateScore() {
        int score = 0;
        for (final Card card : hands.getValue()) {
            score += determineScore(card, score);
        }
        return score;
    }

    private int determineScore(final Card card, final int score) {
        if (card.getRank() == Rank.ACE) {
            return determineAceScore(score);
        }
        return card.getRankValue();
    }

    private int determineAceScore(final int score) {
        if (score + ACE_HIGH <= PERFECT_SCORE) {
            return ACE_HIGH;
        }
        return Rank.ACE.getValue();
    }

    public boolean isPlayer() {
        return false;
    }
    public List<Card> getHands() {
        return Collections.unmodifiableList(hands.getValue());
    }

}

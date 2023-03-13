package blackjack.domain.card;

import blackjack.domain.participant.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final Score BLACKJACK_SCORE = new Score(21);
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final List<Card> hand = new ArrayList<>();

    public void add(Card card) {
        hand.add(card);
    }

    public boolean isBlackJack() {
        return calculateScore().equals(BLACKJACK_SCORE)
                && hand.size() == BLACKJACK_CARD_COUNT;
    }

    public boolean isBust() {
        return calculateScore().isGreaterThan(BLACKJACK_SCORE);
    }

    public Score calculateScore() {
        Score score = sum();
        int aceCount = getAceCount();
        return score.calculateBestScoreAce(aceCount);
    }

    private Score sum() {
        return hand.stream()
                .map(card -> new Score(card.getDenomination().getValue()))
                .reduce(new Score(0), Score::increase);
    }

    private int getAceCount() {
        return (int) hand.stream()
                .filter(card -> card.getDenomination().equals(Denomination.ACE))
                .count();
    }


    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public int getCount() {
        return hand.size();
    }

}

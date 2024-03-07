package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.util.Constants;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    public static final int BLACKJACK_CARD_SIZE = 2;
    private static final int MAX_ACE_SCORE = 11;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card... card) {
        this.cards.addAll(List.of(card));
    }

    public long calculateScore() {
        long aceCount = countAce();
        int scoreWithoutAce = calculateScoreWithoutAce();

        int residualScore = Constants.BLACKJACK_BOUND - scoreWithoutAce;

        int quotient = residualScore / MAX_ACE_SCORE;
        long minAceCount = Math.min(quotient, aceCount);

        return calculateAce(aceCount, scoreWithoutAce, minAceCount);
    }

    private long calculateAce(final long aceCount, final int scoreWithoutAce, final long minAceCount) {
        if (aceCount > 0) {
            return scoreWithoutAce + minAceCount * MAX_ACE_SCORE + (aceCount - minAceCount);
        }

        return scoreWithoutAce;
    }

    private int calculateScoreWithoutAce() {
        return cards.stream()
                .filter(card -> (card.getRank() != Rank.ACE))
                .mapToInt(c -> c.getScore().get(0))
                .sum();
    }

    private long countAce() {
        return cards.stream()
                .filter(card -> (card.getRank() == Rank.ACE))
                .count();
    }

    public boolean isBlackjack() {
        return calculateScore() == Constants.BLACKJACK_BOUND && cards.size() == BLACKJACK_CARD_SIZE;
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public List<Card> getCards() {
        return cards;
    }
}

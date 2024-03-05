package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int SCORE_GOAL = 21;
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

        int residualScore = SCORE_GOAL - scoreWithoutAce;

        int quotient = residualScore / MAX_ACE_SCORE;
        long minAceCount = Math.min(quotient, aceCount);

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

    public List<Card> getCards() {
        return cards;
    }
}

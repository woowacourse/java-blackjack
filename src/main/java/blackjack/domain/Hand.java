package blackjack.domain;

import blackjack.domain.card.TrumpCard;
import blackjack.domain.card.Rank;
import blackjack.util.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    public static final int BLACKJACK_CARD_SIZE = 2;
    private static final int MAX_ACE_SCORE = 11;

    private final List<TrumpCard> trumpCards;

    public Hand() {
        this.trumpCards = new ArrayList<>();
    }

    public void add(final TrumpCard... trumpCard) {
        trumpCards.addAll(List.of(trumpCard));
    }

    public long calculateScore() {
        long aceCount = countAce();
        int scoreWithoutAce = calculateScoreWithoutAce();

        int residualScore = Constants.BLACKJACK_BOUND - scoreWithoutAce;

        int quotient = residualScore / MAX_ACE_SCORE;
        long minAceCount = Math.min(quotient, aceCount);

        return calculate(aceCount, scoreWithoutAce, minAceCount);
    }

    private long countAce() {
        return trumpCards.stream()
                .filter(card -> (card.getRank() == Rank.ACE))
                .count();
    }

    private int calculateScoreWithoutAce() {
        return trumpCards.stream()
                .filter(card -> (card.getRank() != Rank.ACE))
                .mapToInt(c -> c.getScore().get(0))
                .sum();
    }

    private long calculate(final long aceCount, final int scoreWithoutAce, final long minAceCount) {
        if (aceCount > 0) {
            return scoreWithoutAce + minAceCount * MAX_ACE_SCORE + (aceCount - minAceCount);
        }

        return scoreWithoutAce;
    }

    public boolean isBlackjack() {
        return calculateScore() == Constants.BLACKJACK_BOUND && trumpCards.size() == BLACKJACK_CARD_SIZE;
    }

    public TrumpCard getFirstCard() {
        return trumpCards.get(0);
    }

    public List<TrumpCard> getCards() {
        return Collections.unmodifiableList(trumpCards);
    }
}

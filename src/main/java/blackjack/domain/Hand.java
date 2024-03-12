package blackjack.domain;

import blackjack.domain.card.TrumpCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int BLACKJACK_BOUND = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;
    private static final int MAX_ACE_SCORE = 11;
    private static final String NO_CARD_EXCEPTION = "손패에 카드가 부족하여 첫 번째 카드를 가져올 수 없습니다.";

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

        int residualScore = BLACKJACK_BOUND - scoreWithoutAce;

        int quotient = residualScore / MAX_ACE_SCORE;
        long minAceCount = Math.min(quotient, aceCount);

        return calculate(aceCount, scoreWithoutAce, minAceCount);
    }

    private long countAce() {
        return trumpCards.stream()
                .filter(TrumpCard::isAce)
                .count();
    }

    private int calculateScoreWithoutAce() {
        return trumpCards.stream()
                .filter(trumpCard -> !trumpCard.isAce())
                .mapToInt(TrumpCard::getScore)
                .sum();
    }

    private long calculate(final long aceCount, final int scoreWithoutAce, final long minAceCount) {
        if (aceCount > 0) {
            return scoreWithoutAce + minAceCount * MAX_ACE_SCORE + (aceCount - minAceCount);
        }

        return scoreWithoutAce;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_BOUND;
    }

    public boolean isBlackjack() {
        return calculateScore() == BLACKJACK_BOUND && trumpCards.size() == BLACKJACK_CARD_SIZE;
    }

    public TrumpCard getFirstCard() {
        if (trumpCards.isEmpty()) {
            throw new IllegalStateException(NO_CARD_EXCEPTION);
        }

        return trumpCards.get(0);
    }

    public List<TrumpCard> getCards() {
        return Collections.unmodifiableList(trumpCards);
    }
}

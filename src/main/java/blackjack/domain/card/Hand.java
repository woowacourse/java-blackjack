package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public final class Hand {

    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_SIZE = 2;
    private static final int ACE_BONUS_SCORE = 11;
    private static final int MAKE_BONUS_SCORE = BLACKJACK_SCORE - ACE_BONUS_SCORE;

    private final List<Card> hand;

    public Hand(final List<Card> hand) {
        this.hand = hand;
    }

    public int totalScore() {
        int score = preCalculate();

        if (canPlusScore(score)) {
            score += MAKE_BONUS_SCORE;
        }
        return score;
    }

    private int preCalculate() {
        return this.hand.stream()
                .mapToInt(Card::score)
                .sum();
    }

    private boolean canPlusScore(final int sum) {
        return containsAce() && (sum <= ACE_BONUS_SCORE);
    }

    public Hand add(Card card) {
        this.hand.add(card);
        return new Hand(this.hand);
    }

    private boolean containsAce() {
        return this.hand.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return this.totalScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return hand.size() == BLACKJACK_SIZE && totalScore() == BLACKJACK_SCORE;
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }
}

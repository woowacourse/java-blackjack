package blackjack.domain.card;

import blackjack.domain.game.Score;
import java.util.ArrayList;
import java.util.List;

public record Hand(List<Card> cards) {
    private static final int ACE_ADJUST_AMOUNT = 10;
    private static final int BUST_THRESHOLD = 21;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    public static Hand empty() {
        return new Hand(new ArrayList<>());
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public Score calculateScore() {
        int sum = calculateRawSum();
        if (canApplyAceAmount(sum)) {
            return new Score(sum + ACE_ADJUST_AMOUNT);
        }
        return new Score(sum);
    }

    private int calculateRawSum() {
        return cards.stream()
            .mapToInt(Card::getValue)
            .sum();
    }

    private boolean canApplyAceAmount(int sum) {
        return containsAce() && (sum + ACE_ADJUST_AMOUNT) <= BUST_THRESHOLD;
    }

    private boolean containsAce() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return calculateScore().isBiggerThan(BUST_THRESHOLD);
    }

    public boolean isBlackjack() {
        Score score = calculateScore();
        return score.isEqualTo(BLACKJACK_SCORE) & hasBlackjackCardCount();
    }

    private boolean hasBlackjackCardCount() {
        return cards.size() == BLACKJACK_CARD_COUNT;
    }
}

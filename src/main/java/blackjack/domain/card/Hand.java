package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Hand {

    private static final int BLACKJACK_SIZE = 2;

    private final List<Card> hand;

    public Hand(final List<Card> hand) {
        this.hand = new ArrayList<>(hand);
    }

    public static Hand generateEmptyCards() {
        return new Hand(new ArrayList<>());
    }

    public Score calculateScoreForBlackjack() {
        if (containsAce()) {
            return calculate().plusIfSoftHand();
        }

        return calculate();
    }

    private Score calculate() {
        return new Score(hand.stream()
                .mapToInt(Card::convertToBlackjackScore)
                .sum());
    }

    private boolean containsAce() {
        return hand.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        Score score = calculateScoreForBlackjack();
        return score.isBust();
    }

    public boolean isBlackjack() {
        Score score = calculateScoreForBlackjack();
        return score.isBlackjack() && hand.size() == BLACKJACK_SIZE;
    }

    public Hand add(final Card card) {
        final List<Card> newHand = new ArrayList<>(hand);
        newHand.add(card);

        return new Hand(newHand);
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }
}

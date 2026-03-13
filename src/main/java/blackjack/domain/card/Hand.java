package blackjack.domain.card;

import blackjack.domain.result.Score;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int ACE_ADJUST_AMOUNT = 10;
    private static final int BUST_THRESHOLD = 21;

    private final List<Card> cards;

    public Hand() {
        this(new ArrayList<>());
    }

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public Score getScore() {
        int sum = calculateSum(cards);
        boolean containsAce = containsAce(cards);
        if (containsAce && canApplyAceAmount(sum)) {
            return new Score(sum + ACE_ADJUST_AMOUNT);
        }
        return new Score(sum);
    }

    private int calculateSum(List<Card> cards) {
        return cards.stream().mapToInt(Card::getValue).sum();
    }

    private boolean containsAce(List<Card> cards) {
        return cards.stream().anyMatch(Card::isAce);
    }

    private boolean canApplyAceAmount(final int sum) {
        return (sum + ACE_ADJUST_AMOUNT) <= BUST_THRESHOLD;
    }

    public boolean isBust() {
        return getScore().value() > BUST_THRESHOLD;
    }
}

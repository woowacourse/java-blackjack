package blackjack.domain.card;

import blackjack.domain.game.Score;
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

    public boolean isBust() {
        return getScore().isBiggerThan(BUST_THRESHOLD);
    }

    public Score getScore() {
        int sum = calculateSum();
        if (canApplyAceAmount(sum)) {
            return new Score(sum + ACE_ADJUST_AMOUNT);
        }
        return new Score(sum);
    }

    private int calculateSum() {
        return cards.stream()
            .mapToInt(Card::getValue)
            .sum();
    }

    private boolean canApplyAceAmount(int sum) {
        return containsAce() && (sum + ACE_ADJUST_AMOUNT) <= BUST_THRESHOLD;
    }

    private boolean containsAce() {
        return cards.stream().anyMatch(Card::isAce);
    }
}

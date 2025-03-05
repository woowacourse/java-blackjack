package domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CardHand {
    private static final int MIN_ACE_SCORE = 1;
    private static final int MAX_ACE_SCORE = 11;
    private static final int INITIAL_CARD_COUNT = 2;
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int MAX_SCORE = 21;

    private final Set<Card> cards;

    public CardHand(Set<Card> cards) {
        this.cards = new HashSet<>(cards);
    }

    public void add(Card newCard) {
        cards.add(newCard);
    }

    public int calculateScore() {
        if (cards.stream().noneMatch(Card::isAce)) {
            return cards.stream()
                    .mapToInt(Card::score)
                    .sum();
        }
        return calculateScoreWithAce();
    }

    public boolean isBust() {
        return calculateScore() > 21;
    }

    private int calculateScoreWithAce() {
        int minimumSum = cards.stream()
                .mapToInt(Card::score)
                .sum();
        int threshold = MAX_SCORE - (MAX_ACE_SCORE - MIN_ACE_SCORE);
        if (minimumSum <= threshold) {
            return minimumSum - MIN_ACE_SCORE + MAX_ACE_SCORE;
        }
        return minimumSum;
    }

    public boolean isBlackJack() {
        return calculateScore() == MAX_SCORE;
    }

    public boolean isDealerHit() {
        return cards.size() == INITIAL_CARD_COUNT && calculateScore() <= DEALER_HIT_THRESHOLD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CardHand cardHand = (CardHand) o;
        return Objects.equals(cards, cardHand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}

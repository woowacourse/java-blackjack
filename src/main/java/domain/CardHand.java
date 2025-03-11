package domain;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CardHand {
    private static final int MIN_ACE_SCORE = 1;
    private static final int MAX_ACE_SCORE = 11;
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int MAX_SCORE = 21;

    private final Set<Card> cards;

    public CardHand(Set<Card> cards) {
        this.cards = new LinkedHashSet<>(cards);
    }

    public void add(Card newCard) {
        cards.add(newCard);
    }

    public int calculateScore() {
        int totalScore = cards.stream()
                .mapToInt(Card::score)
                .sum();
        if (hasAce() && totalScore <= 11) {
            return totalScore + 10;
        }
        return totalScore;
    }

    public boolean isBust() {
        return calculateScore() > 21;
    }

    public boolean isBlackJack() {
        return calculateScore() == MAX_SCORE;
    }

    public boolean doesDealerNeedCard() {
        return calculateScore() <= DEALER_HIT_THRESHOLD;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
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

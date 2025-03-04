package domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CardHand {
    private final int MAX_SCORE = 21;

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

    private int calculateScoreWithAce() {
        int MIN_ACE_SCORE = 1;
        int MAX_ACE_SCORE = 11;
        int minimumSum = cards.stream()
                .mapToInt(Card::score)
                .sum();
        int threshold = MAX_SCORE - (MAX_ACE_SCORE - MIN_ACE_SCORE);
        if (minimumSum <= threshold) {
            return minimumSum - MIN_ACE_SCORE + MAX_ACE_SCORE;
        }
        return minimumSum;
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

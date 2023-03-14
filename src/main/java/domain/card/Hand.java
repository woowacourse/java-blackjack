package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hand {

    private static final int BLACKJACK_CARD_SIZE = 2;
    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Hand(final Card card) {
        this.cards = new ArrayList<>(List.of(card));
    }

    public Hand(final Card card1, final Card card2) {
        this.cards = new ArrayList<>(List.of(card1, card2));
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public Score score() {
        if (hasAce() && sum().isMoreThan(Score.blackjack())) {
            return sum().minusTenIfBust();
        }

        return sum();
    }

    private Score sum() {
        return cards.stream()
                .map(Card::score)
                .reduce(Score.min(), Score::sum);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return score().isOverBlackjack();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public boolean isBlackjack() {
        return score().isBlackjack() && cards.size() == BLACKJACK_CARD_SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }
}

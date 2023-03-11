package domain.user;

import domain.card.Card;
import domain.game.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Hand(Card card) {
        this.cards = new ArrayList<>(List.of(card));
    }

    public Hand(Card card1, Card card2) {
            this.cards = new ArrayList<>(List.of(card1, card2));
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Score score() {
        if (hasAce()) {
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
        return score().isOverMax();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public boolean isBlackjack() {
        return score().isMax() && cards.size() == 2;
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

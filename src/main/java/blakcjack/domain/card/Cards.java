package blakcjack.domain.card;

import blakcjack.domain.score.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {
    private static final int BLACKJACK_COUNT = 2;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        final Score score = calculateMinScore();
        if (hasAce()) {
            return score.applyAsHigherAce();
        }
        return score;
    }

    private Score calculateMinScore() {
        final int sum = cards.stream()
                .mapToInt(Card::getCardNumberValue)
                .sum();

        return Score.from(sum);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return calculateScore().isBust();
    }

    public boolean isBlackJack() {
        return cards.size() == BLACKJACK_COUNT && calculateScore().isBlackJackValue();
    }

    public boolean isLowerThanBlackJack() {
        return calculateScore().isLowerThanBlackJackValue();
    }

    public List<Card> toList() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}

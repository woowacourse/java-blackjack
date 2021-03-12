package blakcjack.domain.card;

import blakcjack.domain.score.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {
    private static final int FIRST_CARD_INDEX = 0;

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

    public List<Card> toList() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> toListOnlyFirstCard() {
        if (cards.isEmpty()) {
            throw new EmptyCardsException();
        }
        return Collections.singletonList(cards.get(FIRST_CARD_INDEX));
    }

    public boolean isBust() {
        return calculateScore().isBust();
    }

    public boolean isLowerThanBlackJack() {
        return calculateScore().isLowerThanBlackJackValue();
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

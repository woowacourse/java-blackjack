package blakcjack.domain.card;

import blakcjack.domain.score.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
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
}

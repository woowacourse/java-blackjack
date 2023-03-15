package blackjackgame.domain.card;

import java.util.ArrayList;
import java.util.List;

import blackjackgame.domain.Score;

public class Hand {
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Hand(Card... cards) {
        this(List.of(cards));
    }

    public Hand add(Card card) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);

        return new Hand(newCards);
    }

    public Score score() {
        if (hasAce()) {
            return sum().plusTenIfNotBurst();
        }
        return sum();
    }

    private Score sum() {
        return cards.stream()
            .map(Card::score)
            .reduce(Score.min(), Score::add);
    }

    private boolean hasAce() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }

    public boolean isBust() {
        return sum().isOverMax();
    }

    public boolean isBlackJack() {
        return score().isMax() && cards.size() == 2;
    }
}

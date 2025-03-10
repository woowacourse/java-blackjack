package blackjack.domain.participants;

import static blackjack.domain.card.Cards.BUST_THRESHOLD;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Player {
    private final String name;
    private final Cards cards;

    public Player(String name, Cards cards) {
        this.name = name.trim();
        this.cards = cards;
    }

    public void take(Card... cards) {
        this.cards.take(cards);
    }

    public boolean canTake() {
        int minScore = cards.calculateMinScore();
        return minScore < BUST_THRESHOLD;
    }

    public int calculateMaxScore() {
        return cards.calculateMaxScore();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public Cards getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }
}

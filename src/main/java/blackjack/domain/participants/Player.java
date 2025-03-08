package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Player {
    private final String name;
    private final Cards cards;

    public Player(String name, Cards cards) {
        this.name = name.trim();
        this.cards = cards;
    }

    public void send(Card... cards) {
        this.cards.take(cards);
    }

    public int calculateMaxScore() {
        return cards.calculateMaxScore();
    }

    public Cards getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }
}

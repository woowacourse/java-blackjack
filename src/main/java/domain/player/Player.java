package domain.player;

import domain.card.Card;
import domain.card.Cards;

public class Player {
    private final String name;
    private final Cards cards;

    public Player(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public boolean isFinished() {
        return cards.isBust();
    }

    public void drawCard(Card card) {
        cards.add(card);
    }

    public Cards getCards() {
        return cards;
    }
}

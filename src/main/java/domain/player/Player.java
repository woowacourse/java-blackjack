package domain.player;

import domain.card.Card;
import domain.card.Cards;

public class Player implements Participant {
    private final String name;
    private final Cards cards;

    public Player(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    @Override
    public boolean isFinished() {
        return cards.isBust();
    }

    @Override
    public void drawCard(Card card) {
        cards.add(card);
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}

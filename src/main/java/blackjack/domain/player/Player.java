package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Player {

    private final Name name;
    protected final Cards cards;

    protected Player(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public abstract void addCard(Card card);

    public abstract boolean canDraw();

    public boolean isBust() {
        return cards.isBust();
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public String getName() {
        return name.getName();
    }

    public Cards getCards() {
        return cards;
    }

    public boolean isBlackjack() {
        return calculateScore() == 21 && cards.size() == 2;
    }
}

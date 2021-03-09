package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Player {

    protected final String name;
    protected final Cards cards;

    public Player(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    abstract void addCard(Card card);

    abstract boolean canDraw();

    public final int calculateScore() {
        return cards.calculateScore();
    }

    public final boolean isBust() {
        return cards.isBust();
    }

    public final boolean isDealer() {
        if (this instanceof Dealer) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}

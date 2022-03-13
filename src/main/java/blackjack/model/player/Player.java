package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

public abstract class Player {
    protected final Cards cards;

    public Player() {
        this.cards = new Cards();
    }

    public void receive(Card card) {
        this.cards.add(card);
    }

    public boolean isBlackJack() {
        return cards.isSameWithLimitScore() && cards.hasTwoCard();
    }

    public Cards getCards() {
        return cards;
    }

    public abstract String getName();

    public abstract boolean isImpossibleHit();
}

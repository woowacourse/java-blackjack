package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Player {
    protected Cards cards;

    public Player(final Cards cards) {
        this.cards = new Cards(cards.getList());
    }

    public void receiveMoreCard(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return cards.isBust();
    }
}

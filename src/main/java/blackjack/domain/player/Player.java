package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Player {
    protected Cards cards;

    public Player(final Cards cards) {
        this.cards = new Cards(cards.getList());
    }

    public abstract List<Card> getInitCards();

    public void receiveMoreCard(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public int getScore() {
        return this.cards.getScore();
    }
}

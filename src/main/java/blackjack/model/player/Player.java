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

    public boolean isImpossibleHit() {
        return this.cards.isTotalScoreOverLimit();
    }

    public void hit(Card card) {
        this.cards.add(card);
    }

    public Cards getDeck() {
        return cards;
    }
}

package blackjack.model.player;

import blackjack.model.card.Card;

public class Dealer extends Player {

    public Dealer(final String name) {
        super(name, Role.DEALER);
    }

    public Card getFirstCard() {
        return this.cards.getFirst();
    }

}

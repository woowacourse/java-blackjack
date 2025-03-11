package blackjack.model.player;

import blackjack.model.card.Card;

public class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME, Role.DEALER);
    }

    public Card getFirstCard() {
        return this.cards.getFirst();
    }

}

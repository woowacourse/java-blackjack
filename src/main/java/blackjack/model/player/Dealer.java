package blackjack.model.player;

import blackjack.model.card.Cards;

public class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public Cards openCards() {
        return new Cards(cards.getFirst());
    }

}

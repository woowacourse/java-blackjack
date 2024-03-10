package domain.gamer;

import domain.cards.Card;
import domain.cards.gamercards.DealerCards;

public class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";

    public Dealer(DealerCards cards) {
        super(DEALER_NAME, cards);
    }

    public boolean canHit() {
        DealerCards dealerCards = (DealerCards) cards;
        return dealerCards.hasScoreUnderHitThreshold();
    }

    public Card openOneCard() {
        return ((DealerCards) cards).pickFirstCard();
    }
}

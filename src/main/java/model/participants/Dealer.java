package model.participants;

import model.card.Card;
import model.casino.Deck;
import model.casino.HitOrHoldPolicy;

public class Dealer extends Player {

    public static final String DEALER_NAME = "딜러";
    public static final int THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isOverThreshold() {
        return getHandTotal() > THRESHOLD;
    }

    public void dealersTurn(Deck deck) {
        bustCheckOfHitOrHold(deck, new HitOrHoldPolicy() {
            @Override
            public boolean hold() {
                return isOverThreshold();
            }
        });
    }

    public Card openOneCard() {
        return hand.getFirstCard();
    }

    public int getCardCount() {
        return hand.getSize();
    }

    public int getExtraSize() {
        return hand.getExtraSize();
    }

    @Override
    public boolean isNotDealer() {
        return false;
    }
}

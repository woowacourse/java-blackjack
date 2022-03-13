package model;

import model.cardGettable.EveryCardsGettable;
import model.cardGettable.FirstCardsGettable;

public class Dealer extends Participator {
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
        this.cardsGettableStrategy = new FirstCardsGettable();
    }

    public void setEveryCardGettable() {
        this.cardsGettableStrategy = new EveryCardsGettable();
    }

    @Override
    public boolean canReceiveCard() {
        return cards.canReceiveCardForDealer();
    }
}

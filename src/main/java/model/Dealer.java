package model;

import java.util.List;
import model.card.Card;
import model.cardGettable.CardsGettable;
import model.cardGettable.EveryCardsGettable;
import model.cardGettable.FirstCardsGettable;

public class Dealer extends Participator {
    public static final String DEALER_NAME = "딜러";

    private CardsGettable behavior;

    public Dealer() {
        super(DEALER_NAME);
        behavior = new FirstCardsGettable();
    }

    public void setEveryCardGettable() {
        this.behavior = new EveryCardsGettable();
    }

    @Override
    public List<Card> getCards() {
        return behavior.getCards(cards);
    }

    @Override
    public boolean canReceiveCard() {
        return cards.canReceiveCardForDealer();
    }
}

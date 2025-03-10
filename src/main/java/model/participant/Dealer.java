package model.participant;

import static constant.BlackjackConstant.DEALER_CARDS_MIN_TOTAL;

import model.card.Card;

public class Dealer extends Participant {

    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean canReceiveCard() {
        return score() < DEALER_CARDS_MIN_TOTAL;
    }

    public Card getLastCard() {
        return getCards().getLast();
    }
}

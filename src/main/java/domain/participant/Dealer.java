package domain.participant;

import domain.card.Card;
import java.util.List;

public class Dealer extends Participant {
    public static final int RECEIVE_CARD_CONDITION = 16;

    public Dealer(List<Card> hand) {
        super(hand);
    }

    public boolean canReceiveCard() {
        return calculateScore() <= RECEIVE_CARD_CONDITION;
    }
}

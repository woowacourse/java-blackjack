package domain.participant;

import domain.card.Card;
import java.util.List;

public class Dealer extends Participant {
    public Dealer(List<Card> hand) {
        super(hand);
    }

    public boolean canReceiveCard() {
        return calculateScore() <= 16;
    }
}

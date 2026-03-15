package domain.participant;

import domain.BlackJackInfo;
import domain.card.Card;

public class Dealer extends Participant {
    public Dealer() {
    }

    public Card getFirstCard() {
        return status.getFirstCard();
    }

    public boolean isReceiveCard() {
        return getScore() <= BlackJackInfo.DEALER_THRESHOLD_SCORE;
    }
}

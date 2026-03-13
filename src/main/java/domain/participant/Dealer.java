package domain.participant;

import domain.BlackJackInfo;
import domain.card.Card;

public class Dealer extends Participant {
    public Dealer() {
        super("딜러");
    }

    public Card getFirstCard() {
        return status.getCards().getFirst();
    }

    public boolean isReceiveCard() {
        return !isFinished() && getScore() <= BlackJackInfo.DEALER_THRESHOLD_SCORE;
    }
}

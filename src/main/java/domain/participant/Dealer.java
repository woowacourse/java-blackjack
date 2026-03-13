package domain.participant;

import domain.Score;
import domain.card.Card;

public class Dealer extends Participant {
    public Dealer() {
        super("딜러");
    }

    public Card getFirstCard() {
        return status.getCards().getFirst();
    }

    public boolean isReceiveCard() {
        Score score = getScore();
        return !isFinished() && getScore().getValue() <= 16;
    }
}

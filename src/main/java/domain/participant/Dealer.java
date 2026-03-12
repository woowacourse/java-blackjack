package domain.participant;

import domain.Score;
import domain.ScoreStatus;
import domain.card.Card;

public class Dealer extends Participant {
    public Dealer() {
        super("딜러");
    }

    public Dealer(HandCards handCards) {
        super("딜러", handCards);
    }

    public Card getFirstCard() {
        return getHandCards().getFirst();
    }

    public boolean isReceiveCard() {
        Score score = getScore();
        return (score.getScoreStatus().equals(ScoreStatus.STAY)) && (score.getValue() <= 16);
    }
}

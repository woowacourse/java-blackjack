package blackjack.domain;

import java.util.List;

public class Player extends Participant {
    private boolean finish = false;

    public Player(String name, List<Card> cards) {
        super(name, new HoldingCards(cards));
    }

    public void closeTurn() {
        finish = true;
    }

    public boolean isFinished() {
        return super.getHoldingCard().isFullScoreOrBust() || finish;
    }

}

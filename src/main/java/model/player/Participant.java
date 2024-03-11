package model.player;

import java.util.List;
import model.Outcome;
import model.card.Card;

public class Participant extends Player {
    private final static int NUMBER_THRESHOLD = 21;

    public Participant(String name, List<Card> cards) {
        super(name, cards);
    }

    public Outcome findOutcome(Dealer dealer) {
        if (isOverMaximumSum()) {
            return Outcome.LOSE;
        }
        if (dealer.isOverMaximumSum()) {
            return Outcome.WIN;
        }
        return findPlayerOutcome(dealer.findPlayerDifference());
    }

    @Override
    public boolean canReceiveCard() {
        return calculateScore() <= NUMBER_THRESHOLD;
    }
}

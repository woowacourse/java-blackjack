package model.player;

import java.util.List;
import model.Outcome;
import model.card.Card;
import model.card.Cards;

public class Participant extends Player {

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
        return calculateScore() <= MAXIMUM_SUM;
    }
}

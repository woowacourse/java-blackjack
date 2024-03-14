package model.player;

import java.util.List;
import model.Outcome;
import model.card.Card;

public class Participant extends User {

    public Participant(String name, List<Card> cards) {
        super(name, cards);
    }

    public Outcome findOutcome(Dealer dealer) {
        if (isBust()) {
            return Outcome.LOSE;
        }
        if (dealer.isBust()) {
            return Outcome.WIN;
        }
        return findPlayerOutcome(dealer.findPlayerDifference());
    }
}

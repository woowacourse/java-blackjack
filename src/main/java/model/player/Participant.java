package model.player;

import java.util.List;
import model.Outcome;
import model.card.Card;

public class Participant extends User {

    public Participant(String name, List<Card> cards) {
        super(name, cards);
    }

    public Outcome findOutcome(Dealer dealer) {
        if (isNotHit()) {
            return Outcome.LOSE;
        }
        if (dealer.isNotHit()) {
            return Outcome.WIN;
        }
        return findPlayerOutcome(dealer.findPlayerDifference());
    }

    private Outcome findPlayerOutcome(int otherDifference) {
        int difference = findPlayerDifference();
        if (otherDifference > difference) {
            return Outcome.WIN;
        }
        if (otherDifference < difference) {
            return Outcome.LOSE;
        }
        return Outcome.DRAW;
    }
}

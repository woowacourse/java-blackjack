package model.player;

import model.Outcome;
import model.card.Card;

import java.util.List;

public class Participant extends User {

    public Participant(String name, List<Card> cards) {
        super(name, cards);
    }

    public Outcome findOutcome(boolean isOtherNotHit, int otherDifference) {
        if (isNotHit()) {
            return Outcome.LOSE;
        }
        if (isOtherNotHit) {
            return Outcome.WIN;
        }
        return findPlayerOutcome(otherDifference);
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

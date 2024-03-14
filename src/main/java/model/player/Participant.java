package model.player;

import model.GameMoney;
import model.Outcome;
import model.card.Card;
import model.card.Cards;

import java.util.List;

public class Participant extends User {

    private final GameMoney gameMoney;

    public Participant(String name, Cards cards, GameMoney gameMoney) {
        super(name, cards);
        this.gameMoney = gameMoney;
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

    public int calculateRevenue(boolean isOtherNotHit, int otherDifference) {
        return gameMoney.calculateRevenue(findOutcome(isOtherNotHit, otherDifference), cards.findBlackJackState());
    }
}

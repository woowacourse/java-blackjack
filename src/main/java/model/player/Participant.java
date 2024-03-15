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

    public int calculateRevenue(Dealer dealer) {
        return gameMoney.calculateRevenue(
                findOutcome(dealer),
                cards.findBlackJackState());
    }
}

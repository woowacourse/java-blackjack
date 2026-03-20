package domain.participant;

import domain.Result;
import domain.card.Hand;
import domain.state.Hit;
import domain.state.State;

public class Player extends Participant {
    private final Name name;
    private final BetMoney betMoney;

    private Player(State state, Name name, BetMoney betMoney) {
        super(state);
        this.name = name;
        this.betMoney = betMoney;
    }

    public static Player of(Hand hand, String name, String betMoney) {
        return new Player(Hit.of(hand), Name.valueOf(name), BetMoney.valueOf(betMoney));
    }

    public BetMoney getProfit(Result result) {
        return betMoney.multiply(result.getOdds());
    }

    public Name getName() {
        return name;
    }
}

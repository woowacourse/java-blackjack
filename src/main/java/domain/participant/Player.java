package domain.participant;

import domain.BetMoney;
import domain.Result;

public class Player extends Participant {
    private final Name name;
    private final BetMoney betMoney;

    private Player(Name name, BetMoney betMoney) {
        this.name = name;
        this.betMoney = betMoney;
    }

    public static Player of(String name, String betMoney) {
        return new Player(Name.valueOf(name), BetMoney.valueOf(betMoney));
    }

    public BetMoney getProfit(Result result) {
        return betMoney.getProfit(result);
    }

    public Name getName() {
        return name;
    }
}

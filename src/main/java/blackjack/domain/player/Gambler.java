package blackjack.domain.player;

import blackjack.domain.BetMoney;

public class Gambler extends Player {

    private final BetMoney betMoney;

    public Gambler(final String name, final BetMoney betMoney) {
        super(name);
        this.betMoney = betMoney;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    public BetMoney getBetMoney() {
        return betMoney;
    }
}

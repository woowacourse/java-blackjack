package blackjack.domain.player;

import blackjack.domain.BetMoney;

public class Gambler extends Player {

    private BetMoney betMoney;

    public Gambler(final String name) {
        super(name);
    }

    public Gambler(final String name, final BetMoney betMoney) {
        super(name);
        this.betMoney = betMoney;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    public int getBetMoney() {
        return betMoney.getValue();
    }
}

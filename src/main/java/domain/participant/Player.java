package domain.participant;

import domain.Betting;
import domain.Money;
import domain.WinningStatus;
import domain.strategy.PlayerDrawStrategy;

public class Player extends Participant {
    private Money wallet;
    private Betting betting;

    public Player(String name) {
        this(name, new Money(0));
    }

    public Player(String name, Money wallet) {
        super(name, new PlayerDrawStrategy());
        this.wallet = wallet;
        this.betting = Betting.none();
    }

    public double profit() {
        return wallet.amount();
    }

    public void bet(int amount) {
        betting = Betting.of(amount);
        wallet = wallet.minus(betting.amount());
    }

    public void applyRoundResult(WinningStatus status) {
        double settleAmount = betting.settle(status);
        wallet = wallet.plus(settleAmount);
        betting = Betting.none();
    }
}

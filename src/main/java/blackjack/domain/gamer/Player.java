package blackjack.domain.gamer;

import blackjack.domain.money.BettingMoney;

public class Player extends Gamer {

    private BettingMoney bettingMoney;

    public Player(final String name, final String bettingMoney) {
        super(name);
        this.bettingMoney = new BettingMoney(Integer.parseInt(bettingMoney));
    }

    public int getBettingMoney() {
        return bettingMoney.getValue();
    }

    @Override
    public boolean canDrawCard() {
        return !isBusted();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
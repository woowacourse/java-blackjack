package blackjack.domain.player;

public class Gambler extends Player {

    private int betMoney = 1000;

    public Gambler(final String name) {
        super(name);
    }

    public Gambler(final String name, final int betMoney) {
        super(name);
        this.betMoney = betMoney;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    public int getBetMoney() {
        return betMoney;
    }
}

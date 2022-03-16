package blackjack.domain.player;

public class Gambler extends Player {

    private Integer betMoney = 1000;

    public Gambler(final String name) {
        super(name);
    }

    public Gambler(final String name, final Integer betMoney) {
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

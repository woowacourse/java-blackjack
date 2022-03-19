package blackjack.domain.player;

public class Gambler extends Player {

    private static final int DEFAULT_INIT_SIZE = 2;

    private final double money;

    private Gambler(Name name, double money) {
        super(name);
        this.money = money;
    }

    public static Gambler of(Name name, double money) {
        return new Gambler(name, money);
    }

    public boolean isFirstQuestion() {
        return getState().cards().size() <= DEFAULT_INIT_SIZE;
    }

    public double getMoney() {
        return money;
    }

    @Override
    public boolean isHit() {
        return !getState().isFinished();
    }
}

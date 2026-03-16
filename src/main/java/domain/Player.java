package domain;

public class Player extends Participant {
    private static final int HIT_THRESHOLD = 20;

    private final Money bettingMoney;

    private Player(Name name, Money bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }

    public static Player from(String name, Money bettingMoney) {
        return new Player(new Name(name), bettingMoney);
    }

    public Money getBettingMoney() {
        return bettingMoney;
    }

    @Override
    protected int getHitThreshold() {
        return HIT_THRESHOLD;
    }
}

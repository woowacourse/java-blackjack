package blackjackgame.domain;

public class Guest extends Player {
    private static final int BLACKJACK_MAX_SCORE = 21;

    private final Name name;
    private final BettingMoney bettingMoney;

    public Guest(final Name name, final Hand hand, final BettingMoney bettingMoney) {
        super(hand);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public boolean canHit() {
        return getScore() < BLACKJACK_MAX_SCORE;
    }

    public String getName() {
        return name.getName();
    }

    public int getBettingMoney() {
        return bettingMoney.money();
    }
}

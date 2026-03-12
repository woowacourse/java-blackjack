package blackjack.domain;

public class Player extends Participant {
    private static final int MAX_CARD_SUM = 21;

    private final Name name;
    private final BettingAmount bettingAmount;

    public Player(String name, BettingAmount bettingAmount) {
        this.name = new Name(name);
        this.bettingAmount = bettingAmount;
    }

    public String getName() {
        return name.getName();
    }

    public int calculateProfit(double ratio) {
        return bettingAmount.calculateProfit(ratio);
    }

    @Override
    public boolean canReceive() {
        return score.isLess(MAX_CARD_SUM);
    }
}

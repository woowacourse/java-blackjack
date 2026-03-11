package blackjack.model;

public class Player extends Participant {
    private final Name name;
    private final BettingAmount bettingAmount;

    public Player(String name, int money) {
        this.name = new Name(name);
        this.bettingAmount = new BettingAmount(money);
    }

    public double getBettingAmount(GameResult gameResult) {
        return bettingAmount.calculateProfit(gameResult);
    }

    public String getName() {
        return name.getName();
    }

    @Override
    public boolean canReceive() {
        return getScore().isPlayerHitScore();
    }
}

package gameState;

public abstract class GameStatus {

    protected final BettingAmount bettingAmount;

    public GameStatus(BettingAmount bettingAmount) {
        this.bettingAmount = bettingAmount;
    }

    public BettingAmount getBettingAmount() {
        return bettingAmount;
    }

    abstract public int getFinalBenefit();
}

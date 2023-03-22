package gameState;

public class Draw extends GameStatus {

    public Draw(BettingAmount bettingAmount) {
        super(bettingAmount);
    }

    @Override
    public int getFinalBenefit() {
        return bettingAmount.getAmount();
    }
}

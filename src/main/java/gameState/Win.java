package gameState;

public class Win extends GameStatus {

    public Win(BettingAmount bettingAmount) {
        super(bettingAmount);
    }

    @Override
    public int getFinalBenefit() {
        return bettingAmount.getAmount() * 2;
    }
}

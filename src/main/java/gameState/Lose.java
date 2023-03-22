package gameState;

public class Lose extends GameStatus {

    public Lose(BettingAmount bettingAmount) {
        super(bettingAmount);
    }

    @Override
    public int getFinalBenefit() {
        return 0;
    }
}

package gameState;

public class Playing extends GameStatus {

    public Playing(BettingAmount bettingAmount) {
        super(bettingAmount);
    }

    @Override
    public int getFinalBenefit() {
        throw new IllegalStateException("아직 게임 중 입니다.");
    }
}

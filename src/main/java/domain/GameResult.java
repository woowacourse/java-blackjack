package domain;

public enum GameResult {
    LOSE(0),       // 모두 잃는 경우
    BLACKJACK(1.5),  // 1.5배를 받는 경우 (블랙잭)
    WIN(1),

    TIE(0)// 원금을 받는 경우 (일반 승리)
    ;

    private double ratio;

    GameResult(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }
}

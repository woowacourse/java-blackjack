package domain;

public enum GameResult {
    LOSE(-1),       // 모두 잃는 경우
    BLACKJACK(1.5),  // 1.5배를 받는 경우 (블랙잭)
    WIN(1),  // 원금을 받는 경우 (일반 승리)
    TIE(0) // 무승부
    ;

    private final double bettingRatio;

    GameResult(double bettingRatio) {
        this.bettingRatio = bettingRatio;
    }

    public double getBettingRatio() {
        return bettingRatio;
    }
}

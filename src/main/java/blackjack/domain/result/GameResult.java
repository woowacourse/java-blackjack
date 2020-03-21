package blackjack.domain.result;

import blackjack.domain.score.Score;

import java.util.Arrays;

public enum GameResult {
    WIN(new WinStrategy(), new WinRateStrategy()),
    DRAW(new DrawStrategy(), new DrawRateStrategy()),
    LOSE(new LoseStrategy(), new LoseRateStrategy());

    private final GameResultStrategy gameResultStrategy;
    private final MoneyRateStrategy moneyRateStrategy;

    GameResult(GameResultStrategy gameResultStrategy, MoneyRateStrategy moneyRateStrategy) {
        this.gameResultStrategy = gameResultStrategy;
        this.moneyRateStrategy = moneyRateStrategy;
    }

    public static GameResult findByScores(Score dealerScore, Score gamblerScore) {
        validateScore(dealerScore, gamblerScore);
        return Arrays.stream(values())
                .filter(gameResult -> gameResult.fulfill(dealerScore, gamblerScore))
                .findFirst()
                .orElseThrow(AssertionError::new);
    }

    private static void validateScore(Score dealerScore, Score gamblerScore) {
        if (dealerScore == null) {
            throw new IllegalArgumentException("딜러의 점수가 비어있습니다.");
        }
        if (gamblerScore == null) {
            throw new IllegalArgumentException("갬블러의 점수가 비어있습니다.");
        }
    }

    private boolean fulfill(Score dealerScore, Score gamblerScore) {
        return gameResultStrategy.fulfill(dealerScore, gamblerScore);
    }

    public double getResultRate(Score gamblerScore) {
        return this.moneyRateStrategy.getRate(gamblerScore);
    }
}

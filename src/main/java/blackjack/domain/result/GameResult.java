package blackjack.domain.result;

import blackjack.domain.generic.BettingMoney;
import blackjack.domain.score.Score;

import java.util.Arrays;

public enum GameResult {
    WIN(new WinStrategy(), new WinRate()),
    DRAW(new DrawStrategy(), new DrawRate()),
    LOSE(new LoseStrategy(), new LoseRate());

    private final GameResultStrategy gameResultStrategy;
    private final MoneyRate moneyRate;

    GameResult(GameResultStrategy gameResultStrategy, MoneyRate moneyRate) {
        this.gameResultStrategy = gameResultStrategy;
        this.moneyRate = moneyRate;
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
        return gameResultStrategy.fulfill1(dealerScore, gamblerScore);
    }

    public double getApplyRateMoney(Score score, BettingMoney bettingMoney) {
        double rate = this.moneyRate.getRate(score);
        return bettingMoney.multipleRate(rate)
                .getMoney();
    }
}

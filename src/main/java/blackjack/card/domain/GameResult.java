package blackjack.card.domain;

import blackjack.card.domain.gameresult.DrawStrategy;
import blackjack.card.domain.gameresult.GameResultStrategy;
import blackjack.card.domain.gameresult.LoseStrategy;
import blackjack.card.domain.gameresult.WinStrategy;
import blackjack.card.domain.gameresult.rate.DrawRate;
import blackjack.card.domain.gameresult.rate.LoseRate;
import blackjack.card.domain.gameresult.rate.MoneyRate;
import blackjack.card.domain.gameresult.rate.WinRate;

import java.util.Arrays;

public enum GameResult {
    WIN(new WinStrategy(), "승", new WinRate()),
    DRAW(new DrawStrategy(), "무", new DrawRate()),
    LOSE(new LoseStrategy(), "패", new LoseRate());

    private final GameResultStrategy gameResultStrategy;
    private final String message;
    private final MoneyRate moneyRate;

    GameResult(GameResultStrategy gameResultStrategy, String message, MoneyRate moneyRate) {
        this.gameResultStrategy = gameResultStrategy;
        this.message = message;
        this.moneyRate = moneyRate;
    }

    public static GameResult findByCardBundles(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        validate(gamblerCardBundle, dealerCardBundle);
        return Arrays.stream(values())
                .filter(gameResult -> gameResult.fulfill(gamblerCardBundle, dealerCardBundle))
                .findFirst()
                .orElseThrow(AssertionError::new);
    }

    private static void validate(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        if (gamblerCardBundle == null || gamblerCardBundle.isEmpty()) {
            throw new IllegalArgumentException("갬블러의 카드가 없습니다.");
        }
        if (dealerCardBundle == null || dealerCardBundle.isEmpty()) {
            throw new IllegalArgumentException("딜러의 카드가 없습니다.");
        }
    }

    private boolean fulfill(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        return this.gameResultStrategy.fulfill(gamblerCardBundle, dealerCardBundle);
    }

    public double getRate(CardBundle gamblerRate) {
        return this.moneyRate.apply(gamblerRate);
    }

    public String getMessage() {
        return message;
    }
}

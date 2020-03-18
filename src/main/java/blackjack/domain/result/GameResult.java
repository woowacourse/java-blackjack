package blackjack.domain.result;

import blackjack.domain.card.CardBundle;

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

    public double getRate(CardBundle gamblerCardBundle) {
        return this.moneyRate.getRate(gamblerCardBundle);
    }

}

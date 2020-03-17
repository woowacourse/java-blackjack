package blackjack.card.domain;

import blackjack.card.domain.resultstrategy.DrawStrategy;
import blackjack.card.domain.resultstrategy.GameResultStrategy;
import blackjack.card.domain.resultstrategy.LoseStrategy;
import blackjack.card.domain.resultstrategy.WinStrategy;

import java.util.Arrays;

public enum GameResult {
    WIN(new WinStrategy(), "승"),
    DRAW(new DrawStrategy(), "무"),
    LOSE(new LoseStrategy(), "패");

    private final GameResultStrategy gameResultStrategy;
    private final String message;

    GameResult(GameResultStrategy gameResultStrategy, String message) {
        this.gameResultStrategy = gameResultStrategy;
        this.message = message;
    }

    public static GameResult findByComparing(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        validate(gamblerCardBundle, dealerCardBundle);
        return Arrays.stream(values())
                .filter(gameResult -> gameResult.fulfill(gamblerCardBundle, dealerCardBundle))
                .findFirst()
                .orElseThrow(AssertionError::new);
    }

    private static void validate(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        if (gamblerCardBundle == null) {
            throw new IllegalArgumentException("갬블러의 카드가 없습니다.");
        }
        if (dealerCardBundle == null) {
            throw new IllegalArgumentException("딜러의 카드가 없습니다.");
        }
    }

    private boolean fulfill(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        return this.gameResultStrategy.fulfill(gamblerCardBundle, dealerCardBundle);
    }

    public String getMessage() {
        return message;
    }
}

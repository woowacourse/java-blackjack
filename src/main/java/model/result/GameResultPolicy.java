package model.result;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;
import model.cards.Cards;

public enum GameResultPolicy {
    PLAYER_FIRST((player, dealer) -> {
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        return null;
    }),
    DEALER_FIRST((player, dealer) -> {
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        return null;
    }),
    PLAYER_HIGHER((player, dealer) -> {
        if (player.calculateResult() > dealer.calculateResult()) {
            return GameResult.WIN;
        }
        return null;
    }),
    DEALER_HIGHER((player, dealer) -> {
        if (player.calculateResult() < dealer.calculateResult()) {
            return GameResult.LOSE;
        }
        return null;
    });

    private final BiFunction<Cards, Cards, GameResult> rule;

    GameResultPolicy(final BiFunction<Cards, Cards, GameResult> rule) {
        this.rule = rule;
    }

    public static GameResult determineGameResult(final Cards dealerCards, final Cards playerCards) {
        return Arrays.stream(GameResultPolicy.values())
                .map(value -> value.rule.apply(playerCards, dealerCards))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(GameResult.DRAW);
    }
}

package blackjack.controller;

import java.util.Objects;

public class ModeStrategyFactory {
    private static final String WIN_OR_LOSE_GAME = "1";
    private static final String BETTING_GAME = "2";
    private static final String NULL_ERR_MSG = "게임이 선택되지 않았습니다.";
    private static final String INVALID_GAME_SELECTED_ERR_MSG = "게임 선택이 잘못되었습니다.";

    private ModeStrategyFactory() {
    }

    public static ModeStrategy<?> createByIdentifier(String game) {
        Objects.requireNonNull(game, NULL_ERR_MSG);

        if (WIN_OR_LOSE_GAME.equals(game)) {
            return new WinOrLoseMode();
        }
        if (BETTING_GAME.equals(game)) {
            return new BettingMode();
        }

        throw new IllegalArgumentException(INVALID_GAME_SELECTED_ERR_MSG);
    }
}

package blackjack.view;

import blackjack.domain.game.GameResult;

public enum GameResultName {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String name;

    GameResultName(String name) {
        this.name = name;
    }

    public static String convert(GameResult gameResult) {
        return valueOf(gameResult.name()).name;
    }
}

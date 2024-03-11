package blackjack.view;

import blackjack.domain.game.PlayerGameResult;

public enum GameResultName {
    WIN("승"),
    LOSE("패"),
    PUSH("무");

    private final String name;

    GameResultName(String name) {
        this.name = name;
    }

    public static String convert(PlayerGameResult playerGameResult) {
        return valueOf(playerGameResult.name()).name;
    }
}

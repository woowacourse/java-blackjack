package blackjack.view;

import blackjack.domain.game.PlayerWinStatus;

public enum GameResultName {
    WIN("승"),
    LOSE("패"),
    PUSH("무");

    private final String name;

    GameResultName(String name) {
        this.name = name;
    }

    public static String convert(PlayerWinStatus playerWinStatus) {
        return valueOf(playerWinStatus.name()).name;
    }
}

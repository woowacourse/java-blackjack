package view;

import java.util.Arrays;

public enum GameResultSymbol {

    WINNING_SYMBOL("승", true),
    LOSE_SYMBOL("패", false);

    final String symbolName;
    final boolean gameResult;

    GameResultSymbol(String symbolName, boolean gameResult) {
        this.symbolName = symbolName;
        this.gameResult = gameResult;
    }

    public static GameResultSymbol changeToSymbol(boolean winningResult) {
        return Arrays.stream(values())
                .filter(symbol -> symbol.gameResult == winningResult)
                .findFirst()
                .orElseThrow();
    }
}

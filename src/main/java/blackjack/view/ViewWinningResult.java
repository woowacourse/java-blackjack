package blackjack.view;

import blackjack.domain.game.WinningResult;

import java.util.Arrays;

public enum ViewWinningResult {
    WIN("승", WinningResult.WIN),
    LOSE("패", WinningResult.LOSE),
    PUSH("무", WinningResult.PUSH),
    ;

    final String name;
    final WinningResult winningResult;

    ViewWinningResult(final String name, final WinningResult winningResult) {
        this.winningResult = winningResult;
        this.name = name;
    }

    public static String getWinningResultName(final WinningResult winningResult) {
        return Arrays.stream(ViewWinningResult.values())
                .filter(it -> it.winningResult == winningResult)
                .findAny()
                .get()
                .name;
    }
}

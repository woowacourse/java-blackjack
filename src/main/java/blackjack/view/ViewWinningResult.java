package blackjack.view;

import blackjack.domain.gameresult.WinningResult;
import java.util.Arrays;
import java.util.List;

public enum ViewWinningResult {

    WIN(WinningResult.WIN, "승"),
    LOSE(WinningResult.LOSE, "패"),
    PUSH(WinningResult.PUSH, "무"),
    ;

    final WinningResult winningResult;
    final String name;

    ViewWinningResult(WinningResult winningResult, String name) {
        this.winningResult = winningResult;
        this.name = name;
    }

    public int winCount(List<WinningResult> dealerResult) {
        return (int) dealerResult.stream()
            .filter(result -> result == WIN.winningResult)
            .count();
    }

    public int loseCount(List<WinningResult> dealerResult) {
        return (int) dealerResult.stream()
            .filter(result -> result == LOSE.winningResult)
            .count();
    }

    public int pushCount(List<WinningResult> dealerResult) {
        return (int) dealerResult.stream()
            .filter(result -> result == PUSH.winningResult)
            .count();
    }

    public static ViewWinningResult findWinningResult(WinningResult winningResult) {
        return Arrays.stream(ViewWinningResult.values())
            .filter(viewWinningResult -> viewWinningResult.winningResult == winningResult)
            .findFirst()
            .get();
    }

    public String getName() {
        return name;
    }
}

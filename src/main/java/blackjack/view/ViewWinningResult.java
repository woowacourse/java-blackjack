package blackjack.view;

import blackjack.domain.WinningResult;
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
            .filter(result -> result.equals(WIN))
            .count();
    }

    public int loseCount(List<WinningResult> dealerResult) {
        return (int) dealerResult.stream()
            .filter(result -> result.equals(LOSE))
            .count();
    }

    public int pushCount(List<WinningResult> dealerResult) {
        return (int) dealerResult.stream()
            .filter(result -> result.equals(PUSH))
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

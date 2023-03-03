package blackjack.util;

import java.util.List;

public enum WinningResult {

    WIN("승"),
    LOSE("패"),
    PUSH("무"),
    ;

    final String name;

    WinningResult(String name) {
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

    public String getName() {
        return name;
    }
}

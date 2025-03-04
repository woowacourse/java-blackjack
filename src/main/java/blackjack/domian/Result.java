package blackjack.domian;

import java.util.Map;

public class Result {
    private final Map<Player, Map<WinningResult, Integer>> winningResults;

    public Result(Map<Player, Map<WinningResult, Integer>> winningResults) {
        this.winningResults = winningResults;
    }
}

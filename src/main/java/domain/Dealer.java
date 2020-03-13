package domain;

import java.util.HashMap;
import java.util.Map;

public class Dealer extends User {
    private Map<WinningResult, Integer> winningResult;

    public Dealer() {
        cards = new DealerCards();
        winningResult = new HashMap<>();
        for (WinningResult winningResult : WinningResult.values()) {
            this.winningResult.put(winningResult, 0);
        }
    }

    @Override
    public boolean canHit() {
        return !((DealerCards) cards).isOverSixteen();
    }

    public void reflectWinningResult(WinningResult playerWinningResult) {
        if (playerWinningResult == WinningResult.WIN) {
            winningResult.put(WinningResult.LOSE, winningResult.get(WinningResult.LOSE) + 1);
            return;
        }
        if (playerWinningResult == WinningResult.LOSE) {
            winningResult.put(WinningResult.WIN, winningResult.get(WinningResult.WIN) + 1);
            return;
        }
        winningResult.put(WinningResult.DRAW, winningResult.get(WinningResult.DRAW) + 1);
    }

    public Map<WinningResult, Integer> getWinningResult() {
        return winningResult;
    }
}

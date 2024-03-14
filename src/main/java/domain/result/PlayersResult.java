package domain.result;

import domain.Profit;
import domain.WinState;
import domain.gamer.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PlayersResult {

    private final Map<Player, WinState> result;
    private final Map<Player, Profit> profitResult;

    public PlayersResult() {
        this.result = new LinkedHashMap<>();
        this.profitResult = new LinkedHashMap<>();
    }

    public void addResult(Player player, WinState winState) {
        result.put(player, winState);
    }

    public void addProfit(Player player, Profit profit) {
        profitResult.put(player, profit);
    }

    public void calculateProfit() {
        for (Entry<Player, WinState> playerWinState : result.entrySet()) {
            Player player = playerWinState.getKey();
            WinState winState = playerWinState.getValue();
            Profit profit = matchWinStateToProfit(player, winState);
            profitResult.put(player, profit);
        }
    }

    private Profit matchWinStateToProfit(Player player, WinState winState) {
        Profit bettingAmount = profitResult.get(player);
        if (winState == WinState.WIN) {
            return bettingAmount.win();
        }
        if (winState == WinState.LOSE) {
            return bettingAmount.lose();
        }
        if (winState == WinState.BLACKJACK) {
            return bettingAmount.specialWin();
        }
        return bettingAmount.keep();
    }

    public int countWinState(WinState winState) {
        return (int) result.values().stream()
                .filter(value -> value.equals(winState))
                .count();
    }

    public Map<Player, WinState> getResult() {
        return Collections.unmodifiableMap(result);
    }

    public Map<Player, Profit> getProfitResult() {
        return Collections.unmodifiableMap(profitResult);
    }
}

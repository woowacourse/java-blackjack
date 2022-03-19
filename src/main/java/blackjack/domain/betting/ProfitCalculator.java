package blackjack.domain.betting;

import blackjack.domain.participant.Player;
import blackjack.domain.result.WinningResult;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitCalculator {

    private static final EarningRateCalculator earningRateCalculator = new EarningRateCalculator();

    private final Map<Player, WinningResult> playerResult;
    private final Map<Player, Integer> playerProfit = new LinkedHashMap<>();

    public ProfitCalculator(Map<Player, WinningResult> playerResult) {
        this.playerResult = playerResult;
    }

    public void calculate() {
        playerResult.forEach((player, winningResult) -> {
            playerProfit.put(player,
                (calculateProfit(player, winningResult)));
        });
    }

    public Map<Player, Integer> getPlayerProfit() {
        return playerProfit;
    }

    private int calculateProfit(Player player, WinningResult winningResult) {
        return multiplyMoneyAndRate(player, earningRateCalculator.calculate(winningResult));
    }

    private int multiplyMoneyAndRate(Player player, double earningRate) {
        return player.calculateProfit(earningRate);
    }
}

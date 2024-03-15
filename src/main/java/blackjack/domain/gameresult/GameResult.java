package blackjack.domain.gameresult;

import blackjack.domain.participant.Player;

import java.util.Map;

public class GameResult {

    private final Map<Player, Profit> profitResult;

    public GameResult(Map<Player, Profit> profitResult) {
        this.profitResult = profitResult;
    }

    public double getDealerProfit() {
        return -1 * profitResult.values()
                .stream()
                .mapToDouble(Profit::getProfit)
                .sum();
    }

    public Map<Player, Profit> getProfitResult() {
        return profitResult;
    }
}

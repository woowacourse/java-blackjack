package blackjack.domain.betting;

import blackjack.domain.participant.Player;
import blackjack.domain.participant.Result;
import java.util.Map;

public class BettingTable {

    private static final int BONUS_PERCENT = 50;

    private final Map<Player, Profit> expectedProfit;

    public BettingTable(final Map<Player, Profit> expectedProfit) {
        this.expectedProfit = expectedProfit;
    }

    public void addBonus(final Player player) {
        validateExistPlayer(player);
        final Profit profit = expectedProfit.get(player);
        expectedProfit.put(player, profit.increaseByPercent(BONUS_PERCENT));
    }

    private void validateExistPlayer(final Player player) {
        if (!expectedProfit.containsKey(player)) {
            throw new IllegalArgumentException("베팅하지 않은 플레이어입니다.");
        }
    }

    public void updateByResult(final Player player, final Result result) {
        validateExistPlayer(player);
        expectedProfit.put(player, result.calculate(expectedProfit.get(player)));
    }

    public Profit getPlayerProfit(final Player player) {
        validateExistPlayer(player);
        return expectedProfit.get(player);
    }

    public Profit getDealerProfit() {
        final int playerProfit = calculatePlayerProfit();
        return new Profit(-playerProfit);
    }

    private int calculatePlayerProfit() {
        return expectedProfit.values().stream()
                .mapToInt(Profit::getValue)
                .sum();
    }
}

package blackjack.domain.betting;

import blackjack.domain.participant.Player;
import java.util.Map;

public class Betting {

    private final Map<Player, Profit> expectedProfit;

    public Betting(final Map<Player, Profit> expectedProfit) {
        this.expectedProfit = expectedProfit;
    }

    public void addBlackJackBonus(final Player player) {
        validateExistPlayer(player);
        final Profit profit = expectedProfit.get(player);
        expectedProfit.put(player, profit.increaseFiftyPercent());
    }

    private void validateExistPlayer(final Player player) {
        if (!expectedProfit.containsKey(player)) {
            throw new IllegalArgumentException("베팅하지 않은 플레이어입니다.");
        }
    }

    public void fail(final Player player) {
        validateExistPlayer(player);
        final Profit profit = expectedProfit.get(player);
        expectedProfit.put(player, profit.loss());
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

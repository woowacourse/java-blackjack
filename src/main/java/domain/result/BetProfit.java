package domain.result;

import domain.participant.Player;

public record BetProfit(
        String name,
        int profit
) {
    public static BetProfit from(final Player player, final GameResult result) {
        final int profit = result.calculateBetProfit(player.getBetAmount());
        return new BetProfit(player.getName(), profit);
    }
}

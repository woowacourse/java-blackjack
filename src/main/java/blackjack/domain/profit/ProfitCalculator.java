package blackjack.domain.profit;

import blackjack.domain.participant.*;

import java.util.Map;

public class ProfitCalculator {

    private ProfitCalculator() {
    }

    public static ProfitResult calculate(final Dealer dealer, final Map<Player, ResultStatus> playersResult) {
        final ProfitResult profitResult = new ProfitResult();

        for (final Player player : playersResult.keySet()) {
            final int profit = calculateProfitOf(player, playersResult);
            profitResult.recordParticipantsProfit(dealer, player, profit);
        }

        return profitResult;
    }

    private static int calculateProfitOf(final Player player, final Map<Player, ResultStatus> playersResult) {
        final BattingAmount battingAmount = player.getBattingAmount();

        final ResultStatus resultStatus = playersResult.get(player);
        final double earningRate = player.calculateEarningRate(resultStatus);

        return battingAmount.multiply(earningRate);
    }
}

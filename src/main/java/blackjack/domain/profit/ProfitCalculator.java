package blackjack.domain.profit;

import blackjack.domain.batting.BattingAmount;
import blackjack.domain.batting.BattingAmountRepository;
import blackjack.domain.game.ResultStatus;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.Map;

public class ProfitCalculator {
    private final BattingAmountRepository battingAmountRepository;

    public ProfitCalculator(final BattingAmountRepository battingAmountRepository) {
        this.battingAmountRepository = battingAmountRepository;
    }

    public ProfitResult calculate(final Dealer dealer, final Map<Player, ResultStatus> gameResult) {
        final ProfitResult profitResult = new ProfitResult();

        for (final Player player : gameResult.keySet()) {
            final int profit = calculateProfitOf(player, gameResult);
            profitResult.recordParticipantsProfit(dealer, player, profit);
        }

        return profitResult;
    }

    private int calculateProfitOf(final Player player, final Map<Player, ResultStatus> gameResult) {
        final BattingAmount battingAmount = battingAmountRepository.findByPlayer(player);

        final ResultStatus resultStatus = gameResult.get(player);
        final double earningRate = player.calculateEarningRate(resultStatus);

        return battingAmount.multiply(earningRate);
    }
}

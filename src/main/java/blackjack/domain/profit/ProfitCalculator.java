package blackjack.domain.profit;

import blackjack.domain.batting.BattingAmount;
import blackjack.domain.batting.BattingAmountRepository;
import blackjack.domain.game.ResultStatus;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitCalculator {
    private static final int INITIAL_PROFIT = 0;
    private static final int NUMBER_FOR_MAKE_REVERSE_PROFIT = -1;

    private final BattingAmountRepository battingAmountRepository;

    public ProfitCalculator(final BattingAmountRepository battingAmountRepository) {
        this.battingAmountRepository = battingAmountRepository;
    }

    public Map<Participant, Integer> calculate(final Dealer dealer, final Map<Player, ResultStatus> gameResult) {
        final Map<Participant, Integer> result = new LinkedHashMap<>();

        recordParticipantsProfit(dealer, gameResult, result);

        return result;
    }

    private void recordParticipantsProfit(final Dealer dealer,
                                          final Map<Player, ResultStatus> gameResult,
                                          final Map<Participant, Integer> result) {
        result.put(dealer, INITIAL_PROFIT);
        for (final Player player : gameResult.keySet()) {
            final int profit = calculateProfitOf(player, gameResult);

            result.put(player, profit);
            result.put(dealer, profit * NUMBER_FOR_MAKE_REVERSE_PROFIT);
        }
    }

    private int calculateProfitOf(final Player player, final Map<Player, ResultStatus> gameResult) {
        final BattingAmount battingAmount = battingAmountRepository.findByPlayer(player);

        final ResultStatus resultStatus = gameResult.get(player);
        final double earningRate = player.calculateEarningRate(resultStatus);

        return battingAmount.multiply(earningRate);
    }
}

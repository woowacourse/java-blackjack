package blackjack.domain;

import blackjack.domain.bet.BetAmount;
import blackjack.domain.bet.BetLeverage;
import blackjack.domain.bet.BetRevenue;
import blackjack.domain.player.PlayerName;
import blackjack.dto.BetRevenueResultDto;
import java.util.LinkedHashMap;
import java.util.Map;

public class BetAmountRepository {

    private final Map<PlayerName, BetAmount> playersBetAmount;

    public BetAmountRepository(final Map<PlayerName, BetAmount> playersBetAmount) {
        this.playersBetAmount = playersBetAmount;
    }

    public BetRevenueResultDto calculateBetRevenue(final Map<PlayerName, BetLeverage> playersBetLeverage) {
        final Map<PlayerName, BetRevenue> playersBetRevenue = calculatePlayersBetRevenue(playersBetLeverage);
        final BetRevenue dealerRevenue = calculateDealerRevenue(playersBetRevenue);

        return BetRevenueResultDto.of(playersBetRevenue, dealerRevenue);
    }

    private Map<PlayerName, BetRevenue> calculatePlayersBetRevenue(
            final Map<PlayerName, BetLeverage> playersBetLeverage
    ) {
        final Map<PlayerName, BetRevenue> playersBetRevenue = new LinkedHashMap<>();

        playersBetAmount.forEach((name, betAmount) ->
                playersBetRevenue.put(name, playersBetLeverage.get(name).applyLeverage(betAmount)));

        return playersBetRevenue;
    }

    private BetRevenue calculateDealerRevenue(final Map<PlayerName, BetRevenue> playersBetRevenue) {
        final BetRevenue sum = playersBetRevenue.values().stream()
                .reduce(BetRevenue::plus)
                .orElse(new BetRevenue(0F));

        return sum.negate();
    }
}

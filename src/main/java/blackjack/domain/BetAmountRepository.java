package blackjack.domain;

import blackjack.domain.bet.BetAmount;
import blackjack.domain.bet.BetLeverage;
import blackjack.domain.bet.BetRevenue;
import blackjack.domain.player.UserName;
import blackjack.dto.BetRevenueResultDto;
import java.util.LinkedHashMap;
import java.util.Map;

public class BetAmountRepository {

    private final Map<UserName, BetAmount> playersBetAmount;

    public BetAmountRepository(final Map<UserName, BetAmount> playersBetAmount) {
        this.playersBetAmount = playersBetAmount;
    }

    public BetRevenueResultDto calculateBetRevenue(final Map<UserName, BetLeverage> playersBetLeverage) {
        final Map<UserName, BetRevenue> playersBetRevenue = calculatePlayersBetRevenue(playersBetLeverage);
        final BetRevenue dealerRevenue = calculateDealerRevenue(playersBetRevenue);

        return BetRevenueResultDto.of(playersBetRevenue, dealerRevenue);
    }

    private Map<UserName, BetRevenue> calculatePlayersBetRevenue(
            final Map<UserName, BetLeverage> playersBetLeverage
    ) {
        final Map<UserName, BetRevenue> playersBetRevenue = new LinkedHashMap<>();

        playersBetAmount.forEach((name, betAmount) ->
                playersBetRevenue.put(name, playersBetLeverage.get(name).applyLeverage(betAmount)));

        return playersBetRevenue;
    }

    private BetRevenue calculateDealerRevenue(final Map<UserName, BetRevenue> playersBetRevenue) {
        final BetRevenue sum = playersBetRevenue.values().stream()
                .reduce(BetRevenue::plus)
                .orElse(new BetRevenue(0F));

        return sum.negate();
    }
}

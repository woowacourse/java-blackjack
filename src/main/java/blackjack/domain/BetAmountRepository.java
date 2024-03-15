package blackjack.domain;

import blackjack.domain.bet.BetAmount;
import blackjack.domain.bet.BetLeverage;
import blackjack.domain.bet.BetRevenue;
import blackjack.domain.player.PlayerName;
import blackjack.dto.BetRevenueResultDto;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class BetAmountRepository {

    private final Map<PlayerName, BetAmount> playerBetAmount;

    public BetAmountRepository(final Map<PlayerName, BetAmount> playerBetAmount) {
        this.playerBetAmount = playerBetAmount;
    }

    public BetRevenueResultDto calculateBetRevenue(final Map<PlayerName, BetLeverage> playersBetLeverage) {
        final Map<PlayerName, BetRevenue> playersBetRevenue = calculatePlayersBetRevenue(playersBetLeverage);
        final BetRevenue dealerRevenue = calculateDealerRevenue(playersBetRevenue);

        return BetRevenueResultDto.of(playersBetRevenue, dealerRevenue);
    }

    private Map<PlayerName, BetRevenue> calculatePlayersBetRevenue(final Map<PlayerName, BetLeverage> playersBetLeverage) {
        return playerBetAmount.entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey,
                        entry -> playersBetLeverage.get(entry.getKey()).applyLeverage(entry.getValue()),
                        (v1, v2) -> v2,
                        LinkedHashMap::new));
    }

    private BetRevenue calculateDealerRevenue(final Map<PlayerName, BetRevenue> playersBetRevenue) {
        final BetRevenue sum = playersBetRevenue.values().stream()
                .reduce(BetRevenue::plus)
                .orElse(new BetRevenue(0F));

        return sum.negate();
    }
}

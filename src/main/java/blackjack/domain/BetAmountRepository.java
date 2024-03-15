package blackjack.domain;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.bet.BetAmount;
import blackjack.domain.bet.BetLeverage;
import blackjack.domain.bet.BetRevenue;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;
import blackjack.dto.BetRevenueResultDto;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class BetAmountRepository {

    private final Map<Player, BetAmount> playerBetAmount;

    public BetAmountRepository(final Players players, final Map<PlayerName, BetAmount> playerBetAmount) {
        this.playerBetAmount = players.getPlayers().stream()
                .collect(toMap(Function.identity(),
                        player -> playerBetAmount.get(player.getPlayerName()),
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public BetRevenueResultDto calculateBetRevenue(final Map<PlayerName, BetLeverage> playersBetLeverage) {
        final Map<PlayerName, BetRevenue> playersBetRevenue = calculatePlayersBetRevenue(playersBetLeverage);
        final BetRevenue dealerRevenue = calculateDealerRevenue(playersBetRevenue);

        return BetRevenueResultDto.of(playersBetRevenue, dealerRevenue);
    }

    private Map<PlayerName, BetRevenue> calculatePlayersBetRevenue(
            final Map<PlayerName, BetLeverage> playersBetLeverage) {
        final Map<PlayerName, BetRevenue> playersBetRevenue = new LinkedHashMap<>();

        playerBetAmount.forEach((player, betAmount) -> {
            final PlayerName name = player.getPlayerName();
            playersBetRevenue.put(name, playersBetLeverage.get(name).applyLeverage(betAmount));
        });

        return playersBetRevenue;
    }

    private BetRevenue calculateDealerRevenue(final Map<PlayerName, BetRevenue> playersBetRevenue) {
        final BetRevenue sum = playersBetRevenue.values().stream()
                .reduce(BetRevenue::plus)
                .orElse(new BetRevenue(0F));

        return sum.negate();
    }
}

package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProfitResult {
    private final Map<Player, Long> playersProfits;
    private final long dealerProfit;

    public ProfitResult(Map<Player, Long> playersProfits, long dealerProfit) {
        this.playersProfits = playersProfits;
        this.dealerProfit = dealerProfit;
    }

    public static ProfitResult generateByUsers(Players players, Dealer dealer) {
        Map<Player, Long> playersProfits = getPlayersProfits(players, dealer);
        long dealerProfit = getDealerProfit(players, dealer);
        return new ProfitResult(playersProfits, dealerProfit);
    }

    private static Map<Player, Long> getPlayersProfits(Players players, Dealer dealer) {
        return players.getPlayers()
                .stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> player.getProfit(dealer),
                        (p1, p2) -> p1,
                        LinkedHashMap::new));
    }

    private static long getDealerProfit(Players players, Dealer dealer) {
        return players.getPlayers()
                .stream()
                .mapToLong(player -> player.getProfit(dealer) * -1)
                .sum();
    }

    public Map<Player, Long> getPlayersProfits() {
        return playersProfits;
    }

    public long getDealerProfit() {
        return dealerProfit;
    }
}

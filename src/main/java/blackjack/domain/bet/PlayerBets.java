package blackjack.domain.bet;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.PlayerProfits;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerBets {

    private final Map<Player, BetAmout> playerBets;

    public PlayerBets(Map<Player, BetAmout> playerBets) {
        this.playerBets = playerBets;
    }

    public PlayerProfits calculateProfitResult(Dealer dealer) {
        return new PlayerProfits(playerBets.keySet().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> (calculatePlayerProfit(player, dealer)))));
    }

    private Profit calculatePlayerProfit(Player player, Dealer dealer) {
        GameResult gameResult = GameResult.of(dealer, player);
        BetAmout betAmout = playerBets.get(player);
        return betAmout.calculateProfit(gameResult.getProfitLeverage());
    }
}

package blackjack.domain.bet;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.PlayerProfits;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerBets {

    private final Map<Player, BetAmount> playerBets;

    public PlayerBets(Map<Player, BetAmount> playerBets) {
        this.playerBets = playerBets;
    }

    public PlayerProfits calculateProfitResult(Dealer dealer) {
        return new PlayerProfits(playerBets.keySet().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> (calculatePlayerProfit(player, dealer)))));
    }

    private Profit calculatePlayerProfit(Player player, Dealer dealer) {
        GameResult gameResult = GameResult.judge(dealer, player);
        BetAmount betAmount = playerBets.get(player);
        return betAmount.calculateProfit(gameResult.getProfitLeverage());
    }
}

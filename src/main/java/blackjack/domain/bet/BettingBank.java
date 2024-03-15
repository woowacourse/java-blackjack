package blackjack.domain.bet;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.PlayerProfitResult;
import java.util.Map;
import java.util.stream.Collectors;

public class BettingBank {

    private final Map<Player, BetAmout> playerMoneyMap;

    public BettingBank(Map<Player, BetAmout> playerMoneyMap) {
        this.playerMoneyMap = playerMoneyMap;
    }

    public PlayerProfitResult calculateProfitResult(Dealer dealer) {
        return new PlayerProfitResult(playerMoneyMap.keySet().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> (calculatePlayerProfit(player, dealer)))));
    }

    private Profit calculatePlayerProfit(Player player, Dealer dealer) {
        GameResult gameResult = GameResult.of(dealer, player);
        BetAmout betAmout = playerMoneyMap.get(player);
        return betAmout.calculateProfit(gameResult.getProfitLeverage());
    }
}

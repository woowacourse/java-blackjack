package blackjack.domain.bet;

import blackjack.domain.player.Player;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.PlayerGameResult;
import blackjack.domain.result.PlayerProfitResult;
import java.util.Map;
import java.util.stream.Collectors;

public class BettingBank {

    private final Map<Player, Money> playerMoneyMap;

    public BettingBank(Map<Player, Money> playerMoneyMap) {
        this.playerMoneyMap = playerMoneyMap;
    }

    public PlayerProfitResult calculateProfitResult(PlayerGameResult playerGameResult) {
        return new PlayerProfitResult(playerMoneyMap.keySet().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> calculatePlayerProfit(player, playerGameResult))));
    }

    private Profit calculatePlayerProfit(Player player, PlayerGameResult playerGameResult) {
        GameResult gameResultOfPlayer = playerGameResult.findGameResultOfPlayer(player);
        Money betOfPlayer = playerMoneyMap.get(player);
        return betOfPlayer.calculateProfit(gameResultOfPlayer.getProfitLeverage());
    }
}

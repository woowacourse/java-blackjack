package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResults {

    private final Map<Player, Integer> playerProfits;

    private GameResults(final Map<Player, Integer> playerProfits) {
        this.playerProfits = playerProfits;
    }

    public static GameResults calculate(final Players players, final Dealer dealer) {
        final Map<Player, Integer> playerProfits = new LinkedHashMap<>();
        players.getPlayers().forEach(player -> addProfit(player, dealer, playerProfits));
        return new GameResults(playerProfits);
    }

    private static void addProfit(
            final Player player,
            final Dealer dealer,
            final Map<Player, Integer> playerProfits
    ) {
        final GameResult result = GameResult.of(player, dealer);
        playerProfits.put(player, result.calculateProfit(player.getBettingMoney()));
    }

    public int getDealerProfit() {
        return -playerProfits.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<Player, Integer> getPlayerProfits() {
        return playerProfits;
    }
}

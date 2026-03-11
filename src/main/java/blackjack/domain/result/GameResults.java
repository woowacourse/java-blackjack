package blackjack.domain.result;

import static java.util.Collections.unmodifiableMap;

import blackjack.domain.betting.Profit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResults {

    private final Map<Player, Profit> playerProfits;

    private GameResults(final Map<Player, Profit> playerProfits) {
        this.playerProfits = playerProfits;
    }

    public static GameResults calculate(final Players players, final Dealer dealer) {
        final Map<Player, Profit> playerProfits = new LinkedHashMap<>();
        players.getPlayers()
                .forEach(player -> addProfit(player, dealer, playerProfits));
        return new GameResults(playerProfits);
    }

    private static void addProfit(
            final Player player,
            final Dealer dealer,
            final Map<Player, Profit> playerProfits
    ) {
        final GameResult result = GameResult.of(player, dealer);
        playerProfits.put(player, result.calculateProfit(player.getBettingMoney()));
    }

    public Profit getDealerProfit() {
        final int sum = playerProfits.values().stream()
                .mapToInt(Profit::getAmount)
                .sum();
        return new Profit(sum).negate();
    }

    public Map<Player, Profit> getProfitsByPlayer() {
        return unmodifiableMap(playerProfits);
    }
}

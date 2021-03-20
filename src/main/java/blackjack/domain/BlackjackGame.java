package blackjack.domain;

import blackjack.domain.result.Result;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackGame {

    private BlackjackGame() {}

    public static Map<Player, Double> playerEarningResult(Dealer dealer, Players players) {
        Map<Player, Double> playerEarning = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            double earning = earningResult(dealer, player);
            playerEarning.put(player, earning);
        }
        return Collections.unmodifiableMap(playerEarning);
    }

    public static double getDealerEarning(Map<Player, Double> playerEarning) {
        return playerEarning.values()
            .stream()
            .mapToDouble(Double::doubleValue)
            .sum();
    }

    private static double earningResult(Dealer dealer, Player player) {
        double earning = player.earning();
        if (earning == player.getMoney()) {
            earning = player.earning(Result.compareScoreResult(player, dealer));
        }
        return earning;
    }
}

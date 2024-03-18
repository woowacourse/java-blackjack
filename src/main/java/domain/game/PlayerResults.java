package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayerResults {

    private final Map<Player, PlayerResult> resultMap;

    private PlayerResults(Map<Player, PlayerResult> resultMap) {
        this.resultMap = resultMap;
    }

    public static PlayerResults withNoEntry() {
        return new PlayerResults(new HashMap<>());
    }

    public void addResultOf(Player player, Dealer dealer) {
        resultMap.put(player, decideResult(player, dealer));
    }

    public PlayerResult decideResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            return PlayerResult.LOSE;
        }
        return decideResultByBlackjack(player, dealer);
    }

    private PlayerResult decideResultByBlackjack(Player player, Dealer dealer) {
        if (player.isBlackjack() && dealer.isNotBlackjack()) {
            return PlayerResult.BLACKJACK;
        }
        return decideResultByScore(player, dealer);
    }

    private PlayerResult decideResultByScore(Player player, Dealer dealer) {
        if (dealer.isBust()) {
            return PlayerResult.WIN;
        }
        if (player.hasHigherScoreThan(dealer)) {
            return PlayerResult.WIN;
        }
        if (player.hasLowerScoreThan(dealer)) {
            return PlayerResult.LOSE;
        }
        return PlayerResult.TIE;
    }

    public PlayerResult resultOf(Player player) {
        return resultMap.get(player);
    }
}

package domain.game;

import domain.user.BetAmount;
import domain.user.Player;
import java.util.Collections;
import java.util.Map;

public class PlayersMoney {
    public static final int DEALER_MULTIPLIER = -1;
    private final Map<Player, BetAmount> playersMoney;

    public PlayersMoney(Map<Player, BetAmount> playersMoney) {
        this.playersMoney = playersMoney;
    }

    public void changeIfBlackjack() {
        playersMoney.keySet()
                .forEach(this::changeMoneyIfBlackjack);
    }

    private void changeMoneyIfBlackjack(Player player) {
        if (player.isBlackJack()) {
            playersMoney.replace(player, playersMoney.get(player).changeByBlackJack());
        }
    }

    public void changeByPlayerResults(Map<Player, GameResult> playerResults) {
        playersMoney.forEach(
                (player, betAmount) -> playersMoney.replace(player, betAmount.change(playerResults.get(player))));
    }

    public Map<Player, BetAmount> getPlayersMoney() {
        return Collections.unmodifiableMap(playersMoney);
    }
}

package domain.money;

import domain.user.Player;
import java.util.Collections;
import java.util.Map;

public class PlayersMoney {
    public static final int DEALER_MULTIPLIER = -1;
    private final Map<Player, Money> playersMoney;

    public PlayersMoney(Map<Player, Money> playersMoney) {
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

    public int calculateDealerMoney() {
        return playersMoney.values().stream().mapToInt(Money::value).sum() * DEALER_MULTIPLIER;
    }

    public Map<Player, Money> getPlayersMoney() {
        return Collections.unmodifiableMap(playersMoney);
    }
}

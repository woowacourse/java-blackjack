package blackjack.domain.game;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import java.util.Collections;
import java.util.List;

public class PlayerProfits {

    private final List<PlayerProfit> playerProfits;

    public PlayerProfits(Dealer dealer, List<Player> players) {
        playerProfits = players.stream()
                .map(player -> makePlayerProfit(dealer, player))
                .toList();
    }

    public int calculateDealerProfit() {
        int playerProfitSum = playerProfits.stream()
                .mapToInt(PlayerProfit::getProfit)
                .sum();
        return playerProfitSum * -1;
    }

    public List<PlayerProfit> getPlayerProfits() {
        return Collections.unmodifiableList(playerProfits);
    }

    private PlayerProfit makePlayerProfit(Dealer dealer, Player player) {
        if (player.checkBlackjackWithInitialCard()) {
            return PlayerProfit.createWhenPlayerBlackjackWithInitialCard(player);
        }
        if (dealer.isBust()) {
            return PlayerProfit.createWhenDealerBust(player);
        }
        if (player.isBust()) {
            return PlayerProfit.createWhenPlayerBust(player);
        }
        return PlayerProfit.createByWinningType(player, WinningType.parse(player.getPoint(), dealer.getPoint()));
    }
}

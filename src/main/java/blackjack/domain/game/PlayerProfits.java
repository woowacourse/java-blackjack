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

    public List<PlayerProfit> getPlayerProfits() {
        return Collections.unmodifiableList(playerProfits);
    }

    private PlayerProfit makePlayerProfit(Dealer dealer, Player player) {
        if (player.getPoint() == 21 && player.getHand().size() == 2) {
            return PlayerProfit.createWhenPlayerBlackjackWithInitialCard(player);
        }
        if (GameRule.isBust(dealer.getPoint())) {
            return PlayerProfit.createWhenDealerBust(player);
        }
        if (GameRule.isBust(player.getPoint())) {
            return PlayerProfit.createPlayerBust(player);
        }
        WinningType winningType = WinningType.parse(player.getPoint(), dealer.getPoint());
        return PlayerProfit.createByWinningType(player, winningType);
    }
}

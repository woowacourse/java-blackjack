package domain.gamer;

import domain.PlayerResult;
import domain.PlayersResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PlayerWallets {
    private final List<PlayerWallet> playerWallets;

    public PlayerWallets(PlayersResult playersResult) {
        List<PlayerWallet> playerWallets = new ArrayList<>();

        for (Player player : playersResult.getPlayerResults().keySet()) {
            playerWallets.add(new PlayerWallet(player, calculatePlayerProfit(playersResult.getPlayerResults(), player)));
        }
        this.playerWallets = playerWallets;
    }

    private int calculatePlayerProfit(Map<Player, PlayerResult> playerResult, Player player) {
        return (int) (player.getPlayerBettingMoney() * playerResult.get(player).getResultState());
    }

    public List<PlayerWallet> getPlayerWallets() {
        return Collections.unmodifiableList(playerWallets);
    }
}

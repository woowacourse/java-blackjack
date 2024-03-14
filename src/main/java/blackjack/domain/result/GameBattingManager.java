package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.Map;

public class GameBattingManager {

    private final PlayersBatting playersBatting = new PlayersBatting();

    public GameBattingManager() {
    }

    public void registerPlayerBatting(Player player, double batting) {
        playersBatting.registerPlayerBatting(player, batting);
    }

    public void decideWinner(Dealer dealer, Player player) {
        if (playerLosesBattingRule(dealer, player)) {
            decideDealerWin(player);
            return;
        }
        if (playerWinsBattingRule(dealer, player)) {
            decidePlayerWin(player);
            return;
        }
        decideTie(player);
    }

    public double getDealerResult() {
        Map<Player, Double> playerBatting = playersBatting.getPlayerBatting();
        return playerBatting.values().stream()
                .mapToDouble(betting -> -betting)
                .sum();
    }

    public Map<Player, Double> getPlayersResult() {
        return playersBatting.getPlayerBatting();
    }

    private boolean playerWinsBattingRule(Dealer dealer, Player player) {
        return player.isBlackJack() && !dealer.isBlackJack();
    }

    private boolean playerLosesBattingRule(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateTotalScore();
        int playerScore = player.calculateTotalScore();

        if (player.isBusted() && dealer.isBusted()) {
            return true;
        }
        if (player.isBusted() && !dealer.isBusted()) {
            return true;
        }
        return (!dealer.isBusted() && playerScore < dealerScore);
    }

    private void decideDealerWin(Player player) {
        playersBatting.calculateBattingReward(player, -1);
    }

    private void decidePlayerWin(Player player) {
        playersBatting.calculateBattingReward(player, 1.5);
    }

    private void decideTie(Player player) {
        playersBatting.calculateBattingReward(player, 1);
    }
}

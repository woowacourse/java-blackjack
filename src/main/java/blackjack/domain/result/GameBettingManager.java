package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameBettingManager {

    private static final Double WIN_BETTING_150 = 1.5;
    private static final Double WIN_BETTING_100 = 1.0;
    private static final Double TIE_BETTING = 0.0;
    private static final Double LOSE_BETTING = -1.0;

    private final Map<Player, Double> playerBetting = new LinkedHashMap<>();

    public GameBettingManager() {
    }

    public void registerPlayerBetting(Player player, double betting) {
        playerBetting.put(player, betting);
    }

    public void calculatePlayerProfit(Dealer dealer, Player player) {
        double profitRate = decideWinner(dealer, player);
        playerBetting.put(player, playerBetting.get(player) * profitRate);
    }

    public double getDealerResult() {
        return playerBetting.values().stream()
                .mapToDouble(betting -> -betting)
                .sum();
    }

    public Map<Player, Double> getPlayersResult() {
        return playerBetting;
    }

    private double decideWinner(Dealer dealer, Player player) {
        if (playerLoses(dealer, player)) {
            return LOSE_BETTING;
        }
        if (playerWinsWithBlackJack(dealer, player)) {
            return WIN_BETTING_150;
        }
        if (playerWinsWithoutBlackJack(dealer, player)) {
            return WIN_BETTING_100;
        }
        return TIE_BETTING;
    }

    private boolean playerWinsWithBlackJack(Dealer dealer, Player player) {
        return player.isBlackJack() && !dealer.isBlackJack();
    }

    private boolean playerWinsWithoutBlackJack(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateTotalScore();
        int playerScore = player.calculateTotalScore();

        if (!player.isBusted() && dealer.isBusted()) {
            return true;
        }
        return (!player.isBusted() && playerScore > dealerScore);
    }

    private boolean playerLoses(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateTotalScore();
        int playerScore = player.calculateTotalScore();

        if (player.isBusted()) {
            return true;
        }
        return (!dealer.isBusted() && playerScore < dealerScore);
    }
}

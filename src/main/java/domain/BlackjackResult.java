package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackResult {
    private final Map<Player, Long> playerProfit = new LinkedHashMap<>();

    private BlackjackResult(Players players, Dealer dealer, PlayerBets playerBets) {
        for (Player player : players.getPlayers()) {
            add(player, dealer, playerBets);
        }
    }

    public static BlackjackResult of(Players players, Dealer dealer, PlayerBets playerBets) {
        return new BlackjackResult(players, dealer, playerBets);
    }

    public void add(Player player, Dealer dealer, PlayerBets playerBets) {
        int playerScore = player.getCards().calculateScore();
        int dealerScore = dealer.getCards().calculateScore();

        BetAmount betAmount = playerBets.findBy(player);
        int scoreMultiplier = scoreMultiplier(playerScore, dealerScore);

        playerProfit.put(player, betAmount.getBetAmount() * scoreMultiplier);
    }

    private int scoreMultiplier(long playerScore, long dealerScore) {
        if (playerScore > dealerScore) {
            return Policy.WIN_MULTIPLIER;
        }
        if (playerScore < dealerScore) {
            return Policy.LOSE_MULTIPLIER;
        }
        return Policy.DRAW_MULTIPLIER;
    }

    public long dealerProfit() {
        long dealerProfit = Policy.INITIAL_DEALER_PROFIT;
        for (long profit : playerProfit.values()) {
            dealerProfit += profit;
        }
        return -1 * dealerProfit;
    }

    public Map<Player, Long> playerProfits() {
        return playerProfit;
    }
}

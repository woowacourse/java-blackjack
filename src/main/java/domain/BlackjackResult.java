package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackResult {
    private final Map<Player, BetResult> playerBets = new LinkedHashMap<>();

    private BlackjackResult() {
    }

    public static BlackjackResult of() {
        return new BlackjackResult();
    }

    public void add(Player player, long betAmount) {
        if (isBlackjack(player)) {
            playerBets.put(player, BetResult.withBetAmount((long) (betAmount * Policy.BLACKJACK_MULTIPLIER)));
            return;
        }
        playerBets.put(player, BetResult.withBetAmount(betAmount));
    }

    private boolean isBlackjack(Player player) {
        return player.getCards().calculateScore() == Policy.BLACKJACK_NUMBER;
    }

    public void resolveResults(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            resolveResult(player, dealer);
        }
    }

    private void resolveResult(Player player, Dealer dealer) {
        BetResult betResult = playerBets.get(player);
        GameResult gameResult = player.compareResult(dealer);
        playerBets.put(player, betResult.withGameResult(gameResult));
    }

    public long dealerProfit() {
        long dealerProfit = Policy.INITIAL_DEALER_PROFIT;
        for (Map.Entry<Player, BetResult> playerBet : playerBets.entrySet()) {
            dealerProfit += playerProfit(playerBet);
        }
        return -1 * dealerProfit;
    }

    public long playerProfit(Map.Entry<Player, BetResult> playerBet) {
        BetResult betResult = playerBet.getValue();
        GameResult gameResult = betResult.getGameResult();
        if (gameResult == GameResult.WIN) {
            return Policy.WIN_MULTIPLIER * betResult.getBetAmount();
        }
        if (gameResult == GameResult.LOSE) {
            return Policy.LOSE_MULTIPLIER * betResult.getBetAmount();
        }
        return Policy.DRAW_MULTIPLIER;
    }

    public Map<Player, BetResult> getPlayerBets() {
        return playerBets;
    }
}

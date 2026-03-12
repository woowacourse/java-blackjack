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
        if (player.getCards().calculateScore() == 21) {
            playerBets.put(player, BetResult.withBetAmount((long) (betAmount * 1.5)));
            return;
        }
        playerBets.put(player, BetResult.withBetAmount(betAmount));
    }

    public void resolveResults(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            resolveResult(player, dealer);
        }
    }

    private void resolveResult(Player player, Dealer dealer) {
        BetResult betResult = playerBets.get(player);
        GameResult gameResult = compareResult(dealer, player);
        playerBets.put(player, betResult.withGameResult(gameResult));
    }

    public GameResult compareResult(Dealer dealer, Player player) {
        int playerScore = player.getScoreOrZeroIfBust();
        int dealerScore = dealer.getScoreOrZeroIfBust();

        if (playerScore > dealerScore) {
            return GameResult.WIN;
        }
        if (playerScore < dealerScore) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public long dealerProfit() {
        long dealerProfit = 0;
        for (Map.Entry<Player, BetResult> playerBet : playerBets.entrySet()) {
            dealerProfit += playerProfit(playerBet);
        }
        return -1 * dealerProfit;
    }

    public long playerProfit(Map.Entry<Player, BetResult> playerBet) {
        BetResult betResult = playerBet.getValue();
        GameResult gameResult = betResult.getGameResult();
        if (gameResult == GameResult.WIN) {
            return betResult.getBetAmount();
        }
        if (gameResult == GameResult.LOSE) {
            return -1 * betResult.getBetAmount();
        }
        return 0;
    }

    public Map<Player, BetResult> getPlayerBets() {
        return playerBets;
    }
}

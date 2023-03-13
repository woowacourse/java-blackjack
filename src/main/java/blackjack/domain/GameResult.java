package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private static final int BLACKJACK = 21;

    private final Map<String, Integer> playersResult;
    private int dealerResult;

    public GameResult(Dealer dealer, Players players) {
        this.dealerResult = 0;
        this.playersResult = new LinkedHashMap<>();
        calculateProfit(dealer.getScore(), players);
    }

    private void calculateProfit(int dealerScore, Players players) {
        for (Player player : players.getPlayers()) {
            calculateProfitWithDealerAndPlayer(dealerScore, player);
        }
    }

    private void calculateProfitWithDealerAndPlayer(int dealerScore, Player player) {
        if (player.getScore() == BLACKJACK) {
            playerScoreIsBlackJack(dealerScore, player);
            return;
        }
        if (player.getScore() < BLACKJACK) {
            playerScoreLessThanBlackJack(dealerScore, player);
            return;
        }
        playerLose(player);
    }

    private void playerScoreIsBlackJack(int dealerScore, Player player) {
        if (dealerScore == BLACKJACK) {
            draw(player);
            return;
        }
        if (player.getAllCards().size() == 2) {
            playerBlackJack(player);
            return;
        }
        playerWin(player);
    }

    private void playerScoreLessThanBlackJack(int dealerScore, Player player) {
        if (dealerScore == BLACKJACK) {
            playerLose(player);
            return;
        }
        if (dealerScore > BLACKJACK) {
            playerWin(player);
            return;
        }
        dealerScoreLessThanBlackJack(dealerScore, player);
    }

    private void dealerScoreLessThanBlackJack(int dealerScore, Player player) {
        if (dealerScore < player.getScore()) {
            playerWin(player);
            return;
        }
        playerLose(player);
    }

    private void playerWin(Player player) {
        Integer playerBettingAmount = ResultType.WIN.calculateProfit(player.getBettingAmountToInt());
        playersResult.put(player.getName(), playerBettingAmount);
        dealerResult -= playerBettingAmount;
    }

    private void playerLose(Player player) {
        Integer playerBettingAmount = ResultType.LOSE.calculateProfit(player.getBettingAmountToInt());
        playersResult.put(player.getName(), playerBettingAmount);
        dealerResult -= playerBettingAmount;
    }

    private void playerBlackJack(Player player) {
        Integer playerBettingAmount = ResultType.BLACKJACK.calculateProfit(player.getBettingAmountToInt());
        playersResult.put(player.getName(), playerBettingAmount);
        dealerResult -= playerBettingAmount;
    }


    private void draw(Player player) {
        Integer playerBettingAmount = ResultType.DRAW.calculateProfit(player.getBettingAmountToInt());
        playersResult.put(player.getName(), playerBettingAmount);
        dealerResult -= playerBettingAmount;
    }

    public int getDealerResult() {
        return dealerResult;
    }

    public Map<String, Integer> getPlayersResult() {
        return playersResult;
    }
}

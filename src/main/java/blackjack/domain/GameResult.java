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
        calculateProfit(dealer, players);
    }

    private void calculateProfit(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            calculateProfitWithDealerAndPlayer(dealer, player);
        }
    }

    private void calculateProfitWithDealerAndPlayer(Dealer dealer, Player player) {
        if (player.getScore() == BLACKJACK) {
            playerScoreIsBlackJack(dealer, player);
            return;
        }
        if (player.getScore() < BLACKJACK) {
            playerScoreLessThanBlackJack(dealer, player);
            return;
        }
        playerLose(player);
    }

    private void playerScoreIsBlackJack(Dealer dealer, Player player) {
        if (dealer.getScore() == BLACKJACK) {
            draw(player);
            return;
        }
        if (dealer.getScore() != BLACKJACK && player.getAllCards().size() == 2) {
            playerBlackJack(player);
            return;
        }
        playerWin(player);
    }

    private void playerScoreLessThanBlackJack(Dealer dealer, Player player) {
        if (dealer.getScore() == BLACKJACK) {
            playerLose(player);
            return;
        }
        if (dealer.getScore() > BLACKJACK) {
            playerWin(player);
            return;
        }
        dealerScoreLessThanBlackJack(dealer, player);
    }

    private void dealerScoreLessThanBlackJack(Dealer dealer, Player player) {
        if (dealer.getScore() < player.getScore()) {
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

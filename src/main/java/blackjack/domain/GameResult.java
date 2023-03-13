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
        calculateVictoryOrDefeat(dealer, players);
    }

    private void calculateVictoryOrDefeat(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            calculateVictoryOrDefeatWithDealerAndPlayer(dealer, player);
        }
    }

    private void calculateVictoryOrDefeatWithDealerAndPlayer(Dealer dealer, Player player) {
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
        Integer playerBettingAmount = ResultType.WIN.calculateBettingAmount(player.getBettingAmountToInt());
        playersResult.put(player.getName(), playerBettingAmount);
        dealerResult -= playerBettingAmount;
    }

    private void playerLose(Player player) {
        Integer playerBettingAmount = ResultType.LOSE.calculateBettingAmount(player.getBettingAmountToInt());
        playersResult.put(player.getName(), playerBettingAmount);
        dealerResult -= playerBettingAmount;
    }

    private void playerBlackJack(Player player) {
        Integer playerBettingAmount = ResultType.BLACKJACK.calculateBettingAmount(player.getBettingAmountToInt());
        playersResult.put(player.getName(), playerBettingAmount);
        dealerResult -= playerBettingAmount;
    }


    private void draw(Player player) {
        Integer playerBettingAmount = ResultType.DRAW.calculateBettingAmount(player.getBettingAmountToInt());
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

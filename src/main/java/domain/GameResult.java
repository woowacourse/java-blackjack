package domain;

import domain.participants.Dealer;
import domain.participants.Player;
import domain.participants.Players;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private static final int BLACK_JACK = 21;
    private static final Double BLACK_JACK_BONUS = 1.5;
    private final Map<Player, Integer> gameResult;

    public GameResult(Players players) {
        this.gameResult = new HashMap<>();
        setPlayersInitialResult(players);
    }

    public void addBetMoney(Player player, Integer money) {
        gameResult.put(player, money);
    }

    private void setPlayersInitialResult(Players players) {
        gameResult.put(players.findDealer(), 0);
        for (Player player : players.getPlayersWithOutDealer()) {
            gameResult.put(player, 0);
        }
    }

    public void calculatePlayersResult(Players players) {
        for (Player player : players.getPlayersWithOutDealer()) {
            calculatePlayer(players.findDealer(), player);
        }
    }

    private void calculatePlayer(Dealer dealer, Player player) {
        if (getPlayersResult(dealer, player).equals(Result.LOSE)) {
            whenPlayerLose(dealer, player);
            return;
        } else if (getPlayersResult(dealer, player).equals(Result.WIN)) {
            whenPlayerWin(dealer, player);
            return;
        }
        whenPlayerDraw(player);
    }

    private Result getPlayersResult(Dealer dealer, Player player) {
        return isPlayerWin(dealer.getCardsSum(), player.getCardsSum());
    }

    private Result isPlayerWin(int dealerSum, int playerSum) {
        if (playerSum > BLACK_JACK || (dealerSum > playerSum && !(dealerSum > BLACK_JACK))) {
            return Result.LOSE;
        }
        if (dealerSum > BLACK_JACK || dealerSum < playerSum) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    private void whenPlayerLose(Dealer dealer, Player player) {
        int playerMoney = getPlayerProfit(player);
        int dealerMoney = gameResult.get(dealer);
        gameResult.replace(player, -playerMoney);
        gameResult.replace(dealer, (dealerMoney + playerMoney));
    }

    private void whenPlayerWin(Dealer dealer, Player player) {
        int playerMoney = getPlayerProfit(player);
        int dealerMoney = gameResult.get(dealer);
        if (player.getCardsSum() == BLACK_JACK && player.getPlayerCards().size() == 2) {
            gameResult.replace(player, (int) (playerMoney * BLACK_JACK_BONUS));
            gameResult.replace(dealer, dealerMoney - (int) (playerMoney * BLACK_JACK_BONUS));
            return;
        }
        gameResult.replace(player, playerMoney);
        gameResult.replace(dealer, (dealerMoney - playerMoney));
    }

    private void whenPlayerDraw(Player player) {
        gameResult.replace(player, 0);
    }

    public Integer getPlayerProfit(Player player) {
        return gameResult.get(player);
    }
}

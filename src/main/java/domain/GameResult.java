package domain;

import domain.participants.Dealer;
import domain.participants.Player;
import domain.participants.Players;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private static final int BLACK_JACK = 21;
    private static final Double BLACK_JACK_BONUS = 1.5;
    private final Map<Player, BettingMoney> gameResult;

    public GameResult(Players players) {
        this.gameResult = new HashMap<>();
        setPlayersInitialResult(players);
    }

    private void setPlayersInitialResult(Players players) {
        gameResult.put(players.findDealer(), new BettingMoney(0));
        for (Player player : players.getPlayersWithOutDealer()) {
            gameResult.put(player, new BettingMoney(0));
        }
    }

    public void addBetMoney(Player player, BettingMoney money) {
        gameResult.put(player, money);
    }

    public void calculatePlayersResult(Players players) {
        for (Player player : players.getPlayersWithOutDealer()) {
            calculatePlayer(players.findDealer(), player);
        }
    }

    private void calculatePlayer(Dealer dealer, Player player) {
        if (player.getCardsSum() > BLACK_JACK || (dealer.getCardsSum() > player.getCardsSum() && !(dealer.getCardsSum() > BLACK_JACK))) {
            whenPlayerLose(dealer, player);
        }
        else if ((dealer.getCardsSum() > BLACK_JACK || dealer.getCardsSum() < player.getCardsSum())) {
            whenPlayerWin(dealer, player);
        }
        else if(dealer.getCardsSum() == player.getCardsSum()) {
            whenPlayerDraw(player);
        }
    }

    private void whenPlayerLose(Dealer dealer, Player player) {
        int playerMoney = getPlayerProfit(player);
        int dealerMoney = gameResult.get(dealer).getBettingMoney();
        gameResult.replace(player, new BettingMoney(-playerMoney));
        gameResult.replace(dealer, new BettingMoney(dealerMoney + playerMoney));
    }

    private void whenPlayerWin(Dealer dealer, Player player) {
        int playerMoney = getPlayerProfit(player);
        int dealerMoney = gameResult.get(dealer).getBettingMoney();
        if (player.getCardsSum() == BLACK_JACK && player.getPlayerCards().size() == 2) {
            gameResult.replace(player, new BettingMoney((int) (playerMoney * BLACK_JACK_BONUS)));
            gameResult.replace(dealer, new BettingMoney(dealerMoney - (int) (playerMoney * BLACK_JACK_BONUS)));
            return;
        }
        gameResult.replace(player, new BettingMoney(playerMoney));
        gameResult.replace(dealer, new BettingMoney(dealerMoney - playerMoney));
    }

    private void whenPlayerDraw(Player player) {
        gameResult.replace(player, new BettingMoney(0));
    }

    public int getPlayerProfit(Player player) {
        return gameResult.get(player).getBettingMoney();
    }
}

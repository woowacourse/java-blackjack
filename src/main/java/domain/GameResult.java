package domain;

import domain.participants.Dealer;
import domain.participants.Player;
import domain.participants.Players;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private final Map<Player, Integer> gameResult;

    public GameResult(Players players) {
        this.gameResult = new HashMap<>();
        setPlayersInitialResult(players);
    }

    private void setPlayersInitialResult(Players players) {
        gameResult.put(players.findDealer(), 0);
        for (Player player : players.getPlayersWithOutDealer()) {
            gameResult.put(player, 0);
        }
    }

    public void calculatePlayersResult(Players players) {
        for (Player player : players.getPlayersWithOutDealer()) {
            gameResult.replace(player, calculateResult(players.findDealer(),player));
            calculateDealer(players.findDealer(), player);
        }
    }

    public int calculateResult(Dealer dealer,Player player) {
        if (player.isBust() || (dealer.dealerWin(player.getCardsSum()) && !dealer.isBust())) {
            return player.bustMoney();
        }
        else if (dealer.getCardsSum() == player.getCardsSum()) {
            return 0;
        }
        else if(player.isBlackJack()){
            return player.blackJackWinMoney();
        }
        return player.getMoney();
    }

    private void calculateDealer(Dealer dealer, Player player) {
        int oldDealerValue = gameResult.get(dealer);
        gameResult.replace(dealer, oldDealerValue + (-gameResult.get(player)));
    }

    public int getPlayerProfit(Player player) {
        return gameResult.get(player);
    }
}

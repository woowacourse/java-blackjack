package domain;

import domain.participants.Dealer;
import domain.participants.Participant;
import domain.participants.Player;
import domain.participants.Players;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private final Map<Participant, Integer> gameResult;

    public GameResult(Dealer dealer, Players players) {
        this.gameResult = new HashMap<>();
        setPlayersInitialResult(dealer,players);
    }

    private void setPlayersInitialResult(Dealer dealer,Players players) {
        gameResult.put(dealer, 0);
        for (Player player : players.getPlayers()) {
            gameResult.put(player, 0);
        }
    }

    public void calculatePlayersResult(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            gameResult.replace(player, calculateResult(dealer,player));
            calculateDealer(dealer, player);
        }
    }

    public int calculateResult(Dealer dealer,Player player) {
        if (player.isBust() || (dealer.dealerWin(player.getCardsSum()) && !dealer.isBust())) {
            return player.getMoney().bust();
        }
        else if (dealer.getCardsSum() == player.getCardsSum()) {
            return 0;
        }
        else if(player.isBlackJack()){
            return player.getMoney().blackJack();
        }
        return player.getMoney().getBettingMoney();
    }

    private void calculateDealer(Dealer dealer, Player player) {
        int oldDealerValue = gameResult.get(dealer);
        gameResult.replace(dealer, oldDealerValue + (-gameResult.get(player)));
    }

    public int getPlayerProfit(Participant player) {
        return gameResult.get(player);
    }
}

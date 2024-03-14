package domain.result;

import domain.Profit;
import domain.WinState;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import java.util.List;
import java.util.Map;

public class Judge {

    private final PlayersResult playersResult;
    private final DealerResult dealerResult;

    public Judge() {
        this.playersResult = new PlayersResult();
        this.dealerResult = new DealerResult();
    }

    public void initializeProfit(Player player, Profit profit) {
        playersResult.addProfit(player, profit);
    }

    public void decideResult(Gamers gamers) {
        decidePlayersResult(gamers.getPlayers(), gamers.getDealer());
        decideDealerResult();
    }

    private void decidePlayersResult(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            decidePlayerResult(player, dealer);
        }
    }

    private void decidePlayerResult(Player player, Dealer dealer) {
        if (player.isBlackJack()) {
            playersResult.calculateProfit(player, WinState.BLACKJACK);
            return;
        }
        if (player.isBust()) {
            playersResult.calculateProfit(player, WinState.LOSE);
            return;
        }
        if (dealer.isBust()) {
            playersResult.calculateProfit(player, WinState.WIN);
            return;
        }
        WinState playerWinState = decidePlayerWinState(player, dealer);
        playersResult.calculateProfit(player, playerWinState);
    }

    private WinState decidePlayerWinState(Player player, Dealer dealer) {
        int playerScore = player.finalizeCardsScore();
        int dealerScore = dealer.finalizeCardsScore();
        if (playerScore > dealerScore) {
            return WinState.WIN;
        }
        if (playerScore < dealerScore) {
            return WinState.LOSE;
        }
        return WinState.DRAW;
    }

    private void decideDealerResult() {
        dealerResult.calculateProfit(getPlayersProfit());
    }

    public Map<Player, Profit> getPlayersProfit() {
        return playersResult.getResult();
    }

    public Profit getDealerProfit() {
        return dealerResult.getProfitResult();
    }
}

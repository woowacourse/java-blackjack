package domain.result;

import domain.Profit;
import domain.WinState;
import domain.cards.Score;
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
            playersResult.addResult(player, WinState.BLACKJACK);
            return;
        }
        if (player.isBust()) {
            playersResult.addResult(player, WinState.LOSE);
            return;
        }
        if (dealer.isBust()) {
            playersResult.addResult(player, WinState.WIN);
            return;
        }
        WinState playerWinState = decidePlayerWinState(player, dealer);
        playersResult.addResult(player, playerWinState);
    }

    private WinState decidePlayerWinState(Player player, Dealer dealer) {
        Score playerScore = player.finalizeCardsScore();
        Score dealerScore = dealer.finalizeCardsScore();
        int gapOfScore = playerScore.compareScore(dealerScore);
        if (gapOfScore > 0) {
            return WinState.WIN;
        }
        if (gapOfScore < 0) {
            return WinState.LOSE;
        }
        return WinState.DRAW;
    }

    private void decideDealerResult() {
        dealerResult.addResult(WinState.WIN, playersResult.countWinState(WinState.LOSE));
        dealerResult.addResult(WinState.DRAW, playersResult.countWinState(WinState.DRAW));
        dealerResult.addResult(WinState.LOSE, playersResult.countWinState(WinState.WIN));
    }

    public void decideProfit() {
        playersResult.calculateProfit();
        dealerResult.calculateProfit(playersResult.getProfitResult());
    }

    public Map<Player, WinState> getPlayersResult() {
        return playersResult.getResult();
    }

    public Map<Player, Profit> getPlayersProfit() {
        return playersResult.getProfitResult();
    }

    public Map<WinState, Integer> getDealerResult() {
        return dealerResult.getResult();
    }

    public Profit getDealerProfit() {
        return dealerResult.getProfitResult();
    }
}

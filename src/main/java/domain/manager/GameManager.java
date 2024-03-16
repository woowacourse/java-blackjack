package domain.manager;

import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import java.util.List;
import java.util.Map;

public class GameManager {

    private final PlayersWallet playersWallet;
    private final DealerWallet dealerWallet;

    public GameManager() {
        this.playersWallet = new PlayersWallet();
        this.dealerWallet = new DealerWallet();
    }

    public void initializeProfit(Player player, Profit profit) {
        playersWallet.addProfit(player, profit);
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
            playersWallet.calculateProfit(player, GameResult.BLACKJACK);
            return;
        }
        if (player.isBust()) {
            playersWallet.calculateProfit(player, GameResult.LOSE);
            return;
        }
        if (dealer.isBust()) {
            playersWallet.calculateProfit(player, GameResult.WIN);
            return;
        }
        GameResult playerGameResult = decidePlayerWinState(player, dealer);
        playersWallet.calculateProfit(player, playerGameResult);
    }

    private GameResult decidePlayerWinState(Player player, Dealer dealer) {
        int playerScore = player.finalizeCardsScore();
        int dealerScore = dealer.finalizeCardsScore();
        if (playerScore > dealerScore) {
            return GameResult.WIN;
        }
        if (playerScore < dealerScore) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    private void decideDealerResult() {
        dealerWallet.calculateProfit(playersWallet);
    }

    public Map<Player, Profit> getPlayersProfit() {
        return playersWallet.getPlayersProfit();
    }

    public Profit getDealerProfit() {
        return dealerWallet.getProfit();
    }
}

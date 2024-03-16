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
        GameResult gameResult = GameResult.decide(player, dealer);
        playersWallet.calculateProfit(player, gameResult);
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

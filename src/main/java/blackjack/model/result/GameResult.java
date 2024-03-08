package blackjack.model.result;

import blackjack.model.gamer.Player;
import java.util.Map;

public class GameResult {

    private final DealerResult dealerResult = new DealerResult();
    private final PlayersResult playersResult = new PlayersResult();

    public void addDealerWin() {
        dealerResult.addWin();
    }

    public void addDealerLose() {
        dealerResult.addLose();
    }

    public void addDealerTie() {
        dealerResult.addTie();
    }

    public int countDealerWins() {
        return dealerResult.countWins();
    }

    public int countDealerLoses() {
        return dealerResult.countLoses();
    }

    public int countDealerTies() {
        return dealerResult.countTies();
    }

    public void addPlayerWin(Player player) {
        playersResult.addWin(player);
    }

    public void addPlayerLose(Player player) {
        playersResult.addLose(player);
    }

    public void addPlayerTie(Player player) {
        playersResult.addTie(player);
    }

    public Map<Player, Result> getPlayersResult() {
        return playersResult.getPlayerResult();
    }
}

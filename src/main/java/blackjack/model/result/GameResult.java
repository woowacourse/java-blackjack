package blackjack.model.result;

import blackjack.model.gamer.Player;
import java.util.Map;

public class GameResult {

    private final DealerResult dealerResult = new DealerResult();
    private final PlayersResult playersResult = new PlayersResult();

    public GameResult() {
    }

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
        return dealerResult.getDealerResult().get(ResultState.WIN);
    }

    public int countDealerLoses() {
        return dealerResult.getDealerResult().get(ResultState.LOSE);
    }

    public int countDealerTies() {
        return dealerResult.getDealerResult().get(ResultState.TIE);
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

    public Map<Player, ResultState> getPlayersResult() {
        return playersResult.getPlayerResult();
    }
}

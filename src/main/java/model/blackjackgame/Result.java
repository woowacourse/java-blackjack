package model.blackjackgame;

import java.util.HashMap;
import java.util.Map;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;

public class Result {

    private static final String WIN_WORD = "승";
    private static final String FAIL_WORD = "패";
    private static final String DRAW_WORD = "무";

    private final Map<String, String> playerResult;

    public Result(Dealer dealer, Players players) {
        this.playerResult = new HashMap<>();
        createPlayersResult(dealer, players);
    }

    private void createPlayersResult(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            playerResult.put(player.getName(), calculatePlayerResult(dealer.totalNumber(), player.totalNumber()));
        }
    }

    private String calculatePlayerResult(int dealerNumber, int playerNumber) {
        if (dealerNumber < playerNumber) {
            return WIN_WORD;
        }
        if (dealerNumber > playerNumber) {
            return FAIL_WORD;
        }
        return DRAW_WORD;
    }

    public Map<String, String> getPlayerResult() {
        return playerResult;
    }

}

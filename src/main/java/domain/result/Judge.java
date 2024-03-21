package domain.result;

import domain.player.Dealer;
import domain.player.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Judge {

    private final Map<Player, WinState> playersResult;

    public Judge() {
        this.playersResult = new LinkedHashMap<>();
    }

    public void decideResult(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            decidePlayerResult(player, dealer);
        }
    }

    private void decidePlayerResult(Player player, Dealer dealer) {
        if (decidePlayerBust(player)) return;
        if (decidePlayerBlackJack(player, dealer)) return;
        if (decideDealerBust(player, dealer)) return;
        playersResult.put(player, judgePlayerWinState(player, dealer));
    }

    private boolean decidePlayerBust(Player player) {
        if (player.isBust()) {
            playersResult.put(player, WinState.LOSE);
            return true;
        }
        return false;
    }

    private boolean decidePlayerBlackJack(Player player, Dealer dealer) {
        if (player.isBlackJackScore() && !dealer.isBlackJackScore()) {
            playersResult.put(player, WinState.BLACK_JACK);
            return true;
        }
        return false;
    }

    private boolean decideDealerBust(Player player, Dealer dealer) {
        if (dealer.isBust()) {
            playersResult.put(player, WinState.WIN);
            return true;
        }
        return false;
    }

    private WinState judgePlayerWinState(Player player, Dealer dealer) {
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

    public Map<Player, WinState> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }
}

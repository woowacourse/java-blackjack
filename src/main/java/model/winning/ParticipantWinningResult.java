package model.winning;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;

public class ParticipantWinningResult {
    private final Map<Player, WinningResult> result;

    public ParticipantWinningResult(Players players, Dealer dealer) {
        this.result = calculateWinningResult(players, dealer);
    }

    private Map<Player, WinningResult> calculateWinningResult(Players players, Dealer dealer) {
        Map<Player, WinningResult> result = new HashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player, checkPlayerWin(dealer, player));
        }
        return result;
    }

    private WinningResult checkPlayerWin(Dealer dealer, Player player) {
        if (player.isBust()) {
            return WinningResult.LOSE;
        }
        if (dealer.isBust()) {
            return WinningResult.WIN;
        }
        return checkResultIfNotBust(dealer, player);
    }

    private WinningResult checkResultIfNotBust(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateFinalScore();
        int playerScore = player.calculateFinalScore();

        if (dealerScore > playerScore) {
            return WinningResult.LOSE;
        }
        if (dealerScore < playerScore) {
            return WinningResult.WIN;
        }
        return WinningResult.DRAW;
    }

    public Map<Player, WinningResult> getResult() {
        return Collections.unmodifiableMap(result);
    }

    public WinningResult getPlayerGameResult(Player player) {
        return result.get(player);
    }
}

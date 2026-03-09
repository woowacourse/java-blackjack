package domain;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Collections.frequency;

public class GameResult {
    private final Map<String, WinningStatus> playerWinningStatus = new LinkedHashMap<>();

    public GameResult(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            playerWinningStatus.put(player.name(), WinningStatus.of(player, dealer));
        }
    }

    public Map<String, WinningStatus> getPlayerWinningStatus() {
        return Map.copyOf(playerWinningStatus);
    }

    public int dealerWinCount() {
        return frequency(playerWinningStatus.values(), WinningStatus.LOSE);
    }

    public int dealerTieCount() {
        return frequency(playerWinningStatus.values(), WinningStatus.TIE);
    }

    public int dealerLoseCount() {
        return frequency(playerWinningStatus.values(), WinningStatus.WIN);
    }
}

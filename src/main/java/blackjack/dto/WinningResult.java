package blackjack.dto;

import blackjack.domain.Player;
import blackjack.domain.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public record WinningResult(
        Map<String, Boolean> winningResult
) {

    public static WinningResult from(Players players, Player dealer) {
        Map<String, Boolean> winningResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            winningResult.put(player.getName(), player.winsAgainst(dealer));
        }
        return new WinningResult(winningResult);
    }

    public int getWinCountOfDealer() {
        return (int) winningResult.entrySet().stream()
                .filter(entry -> Boolean.FALSE.equals(entry.getValue()))
                .count();
    }

    public int numberOfPlayer() {
        return winningResult.size();
    }

    public boolean get(String playerName) {
        return winningResult.get(playerName);
    }

}

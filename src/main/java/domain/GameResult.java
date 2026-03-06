package domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Collections.frequency;

public class GameResult {
    Map<Player, WinningStatus> playerWinningStatus = new LinkedHashMap<>();

    public GameResult(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            playerWinningStatus.put(player, WinningStatus.of(player, dealer));
        }
    }

    public Map<String, String> getPlayersStatistics() {
        Map<String, String> result = new LinkedHashMap<>();

        for (Player player : playerWinningStatus.keySet()) {
            String name = player.name();
            WinningStatus winningStatus = playerWinningStatus.get(player);

            result.put(name, winningStatus.getSymbol());
        }
        return result;
    }

    public  Map<String, String> getDealerStatistics() {
        int loseCount = frequency(playerWinningStatus.values(), WinningStatus.WIN);
        int tieCount = frequency(playerWinningStatus.values(), WinningStatus.TIE);
        int winCount = frequency(playerWinningStatus.values(), WinningStatus.LOSE);

        return formatStatistics(winCount, tieCount, loseCount);
    }

    private Map<String, String> formatStatistics(int winCount, int tieCount, int loseCount) {
        Map<String, String> dealerStatistics = new LinkedHashMap<>();
        StringBuilder stringBuilder = new StringBuilder();

        if (winCount > 0) {
            stringBuilder.append(winCount).append("승 ");
        }
        if (tieCount > 0) {
            stringBuilder.append(tieCount).append("무 ");
        }
        if (loseCount > 0) {
            stringBuilder.append(loseCount).append("패 ");
        }

        dealerStatistics.put("딜러", stringBuilder.toString());
        return dealerStatistics;
    }
}

package dto;

import java.util.*;
import java.util.stream.Collectors;

public class WinningPlayerResult {
    private Map<String, Boolean> winningPlayer;

    private WinningPlayerResult(Map<String, Boolean> winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public List<String> getWinningResult() {
        int allUserWinCount = (int) winningPlayer.values().stream().filter(win -> win).count();
        int allUserLoseCount = winningPlayer.values().size() - allUserWinCount;
        List<String> result = new ArrayList<>(Collections.singletonList(String.format(
                "딜러: %d승 %d패",allUserLoseCount ,allUserWinCount)));

        result.addAll(winningPlayer.entrySet().stream()
                .map(entry -> winString(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));
        return result;
    }

    private String winString(String name, boolean isWin) {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        if (isWin) {
            sb.append(": 승");
            return sb.toString();
        }
        sb.append(": 패");
        return sb.toString();
    }

    public static WinningPlayerResult of(Map<String, Boolean> winningPlayer) {
        return new WinningPlayerResult(winningPlayer);
    }
}

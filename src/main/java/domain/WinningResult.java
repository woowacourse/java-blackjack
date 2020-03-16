package domain;

import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;

import java.util.*;
import java.util.stream.Collectors;

public class WinningResult {
    private Map<String, Boolean> winningPlayerResult;

    public WinningResult(Players players, Dealer dealer) {
        winningPlayerResult = new HashMap<>();

        for (Player player : players.getPlayers()) {
            winningPlayerResult.put(player.getName(), DecisionWinner.compareWinner(player, dealer));
        }
    }

    public List<String> generateWinningUserResult(Players players) {
        int allUserWinCount = (int) winningPlayerResult.values().stream().filter(win -> win).count();
        int allUserLoseCount = winningPlayerResult.values().size() - allUserWinCount;

        List<String> result = new ArrayList<>(Collections.singletonList(String.format(
                "딜러: %d승 %d패", allUserLoseCount, allUserWinCount))
        );
        result.addAll(players.getPlayers()
                .stream()
                .map(player -> determineWin(player.getName(), winningPlayerResult.get(player.getName())))
                .collect(Collectors.toList())
        );

        return Collections.unmodifiableList(result);
    }

    private String determineWin(String name, boolean isWin) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(name);
        if (isWin) {
            stringBuilder.append(": 승");
            return stringBuilder.toString();
        }
        stringBuilder.append(": 패");
        return stringBuilder.toString();
    }
}

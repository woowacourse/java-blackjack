package domain;

import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;

import java.util.*;
import java.util.stream.Collectors;

public class WinningResult {
    private List<String> winningUserResult;

    public WinningResult(Players players, Dealer dealer) {
        Map<String, Boolean> winningPlayerResult = new HashMap<>();

        for (Player player : players.getPlayers()) {
            winningPlayerResult.put(player.getName(), DecisionWinner.compareWinner(player, dealer));
        }

        this.winningUserResult = generateWinningUserResult(winningPlayerResult);
    }

    private List<String> generateWinningUserResult(Map<String, Boolean> winningPlayerResult) {

        int allUserWinCount = (int) winningPlayerResult.values().stream().filter(win -> win).count();
        int allUserLoseCount = winningPlayerResult.values().size() - allUserWinCount;

        List<String> result = new ArrayList<>(Collections.singletonList(String.format(
                "딜러: %d승 %d패", allUserLoseCount, allUserWinCount))
        );

        result.addAll(winningPlayerResult.keySet()
                .stream()
                .map(player-> determineWin(player,winningPlayerResult.get(player)))
                .collect(Collectors.toList())
        );

        return result;
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

    public List<String> getWinningUserResult() {
        return Collections.unmodifiableList(winningUserResult);
    }
}

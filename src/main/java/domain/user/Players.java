package domain.user;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(Map<String, Integer> playerBettingMoneyTable) {
        return new Players(generatePlayers(playerBettingMoneyTable));
    }

    private static List<Player> generatePlayers(Map<String, Integer> playerBettingMoneyTable) {
        try {
            return playerBettingMoneyTable.keySet().stream()
                    .map(playerNameInput -> Player.of(playerNameInput, playerBettingMoneyTable.get(playerNameInput)))
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("플레이어 생성에 실패했습니다" + System.lineSeparator()
                    + "사유: " + e.getMessage());
        }
    }

    public List<Player> getPlayers() {
        return this.players;
    }
}

package domain.user;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> nameValues) {
        return new Players(generatePlayers(nameValues));
    }

    private static List<Player> generatePlayers(List<String> nameValues) {
        try {
            return nameValues.stream()
                    .map(Player::new)
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

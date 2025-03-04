package blackjack.domain;

import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<Player> players) {
        if (isDuplicate(players)) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름을 입력했습니다.");
        }
    }

    private static boolean isDuplicate(List<Player> players) {
        return players.size() != players.stream()
                .distinct()
                .count();
    }
}

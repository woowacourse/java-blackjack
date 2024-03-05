package domain;

import java.util.List;
import java.util.Set;

public class Players {

    private final List<Player> names;

    private Players(final List<Player> names) {
        this.names = names;
    }

    public static Players from(final List<String> names) {
        List<Player> players = names.stream()
                .map(Player::new)
                .toList();
        validateDuplicate(players);
        return new Players(players);
    }

    private static void validateDuplicate(final List<Player> players) {
        if (players.size() != Set.copyOf(players).size()) {
            throw new IllegalArgumentException("[ERROR] 참여자 이름은 중복될 수 없습니다.");
        }
    }

}

package domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> names) {
        validateDuplicate(names);
        List<Player> players = names.stream()
                                    .map(Player::new)
                                    .collect(Collectors.toList());
        return new Players(players);
    }

    private static void validateDuplicate(List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException("중복인 이름은 입력하실 수 없습니다.");
        }
    }

    public List<Player> toList() {
        return List.copyOf(players);
    }
}

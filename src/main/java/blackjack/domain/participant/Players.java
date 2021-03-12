package blackjack.domain.participant;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {
    public static final String SAME_NAME_ERROR = "중복된 이름을 사용할 수 없습니다.";

    private final List<Player> players;

    public Players(List<String> names) {
        validateSameName(names);
        players = generatePlayers(names);
    }

    private void validateSameName(final List<String> names) {
        final Set<String> changedNames = new HashSet<>(names);
        if (names.size() != changedNames.size()) {
            throw new IllegalArgumentException(SAME_NAME_ERROR);
        }
    }

    private List<Player> generatePlayers(final List<String> names) {
        return names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }
}

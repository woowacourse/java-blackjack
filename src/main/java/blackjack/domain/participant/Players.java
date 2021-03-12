package blackjack.domain.participant;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Players {
    public static final String SAME_NAME_ERROR = "중복된 이름을 사용할 수 없습니다.";

    private final List<Player> players;

    public Players(final List<String> names, final List<Integer> bettingMoneys) {
        validateSameName(names);
        players = generatePlayers(names, bettingMoneys);
    }

    private void validateSameName(final List<String> names) {
        final Set<String> changedNames = new HashSet<>(names);
        if (names.size() != changedNames.size()) {
            throw new IllegalArgumentException(SAME_NAME_ERROR);
        }
    }

    private List<Player> generatePlayers(final List<String> names, final List<Integer> bettingMoneys) {
        return IntStream.range(0, names.size())
                .mapToObj(i -> new Player(names.get(i), bettingMoneys.get(i)))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }
}

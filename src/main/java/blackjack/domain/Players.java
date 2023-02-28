package blackjack.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(final String input) {
        this.players = makePlayers(input);
    }

    private List<Player> makePlayers(final String input) {
        List<String> names = Arrays.asList(input.split(","));
        validateNumberOfPlayers(names);
        validateDuplicatedNames(names);
        return names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private void validateNumberOfPlayers(List<String> names) {
        if (names.size() < 1 || names.size() > 7) {
            throw new IllegalArgumentException("플레이어의 수는 1명 이상 7명 이하만 가능합나다.");
        }
    }

    private void validateDuplicatedNames(List<String> names) {
        Set<String> namesWithoutDuplicate = new HashSet<>(names);
        if (namesWithoutDuplicate.size() != names.size()) {
            throw new IllegalArgumentException("각 플레이어는 중복된 이름을 가질 수 없습니다.");
        }
    }


    public List<Player> getPlayers() {
        return this.players;
    }
}

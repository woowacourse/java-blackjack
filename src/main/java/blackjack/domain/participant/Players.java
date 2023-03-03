package blackjack.domain.participant;

import blackjack.domain.participant.Player;

import java.util.*;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(final String input) {
        this.players = makePlayers(input);
    }

    private List<Player> makePlayers(final String input) {
        final List<Player> names = Arrays.stream(input.split(","))
                .map(Player::new)
                .collect(Collectors.toList());
        validateNumberOfPlayers(names);
        validateDuplicatedNames(names);
        return names;
    }

    private void validateNumberOfPlayers(final List<Player> names) {
        if (names.size() < 1 || names.size() > 7) {
            throw new IllegalArgumentException("플레이어의 수는 1명 이상 7명 이하만 가능합나다.");
        }
    }

    private void validateDuplicatedNames(final List<Player> names) {
        final Set<String> namesWithoutDuplicate = names.stream()
                .map(Player::getName)
                .collect(Collectors.toSet());
        if (namesWithoutDuplicate.size() != names.size()) {
            throw new IllegalArgumentException("각 플레이어는 중복된 이름을 가질 수 없습니다.");
        }
    }

    public List<Player> getPlayers() {
        return this.players;
    }
}

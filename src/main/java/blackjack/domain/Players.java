package blackjack.domain;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Players {

    private final List<Player> players;

    public Players(List<String> names) {
        validate(names);
        this.players = createPlayers(names);
    }

    private void validate(List<String> names) {
        validateNumberOfPlayer(names);
        validateDuplicate(names);
    }

    private void validateNumberOfPlayer(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 참여할 사람은 한 명 이상이어야 합니다.");
        }
    }

    private void validateDuplicate(List<String> names) {
        long uniqueNamesCount = names.stream()
                .distinct()
                .count();
        if (uniqueNamesCount != names.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름이 있습니다.");
        }
    }

    private List<Player> createPlayers(List<String> playersName) {
        return playersName.stream()
                .map(Player::new)
                .collect(toList());

    }

    public List<Player> getPlayers() {
        return players;
    }
}

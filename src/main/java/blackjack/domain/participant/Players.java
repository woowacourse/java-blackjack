package blackjack.domain.participant;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 7;

    private final List<Player> players;

    private Players(List<Player> players) {
        validateCount(players);
        validateDuplicate(players);
        this.players = players;
    }

    private void validateCount(List<Player> players) {
        int playersCount = players.size();
        if (playersCount < MIN_COUNT || playersCount > MAX_COUNT) {
            throw new IllegalArgumentException("참가자 수는 " + MIN_COUNT + "명 이상 " + MAX_COUNT + "명 이하 입니다.");
        }
    }

    private void validateDuplicate(List<Player> players) {
        long distinctCount = players.stream()
                .distinct()
                .count();

        if (distinctCount != players.size()) {
            throw new IllegalArgumentException("참가자는 중복을 허용하지 않습니다.");
        }
    }

    public static Players create(List<String> names) {
        List<Player> players = names.stream()
                .map(Name::new)
                .map(Player::new)
                .collect(Collectors.toList());
        return new Players(players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}

package domain.participant;

import java.util.List;

public class Players {

    private static final int MIN_PLAYER_COUNT = 2;
    private static final int MAX_PLAYER_COUNT = 8;

    private final List<Player> value;

    public Players(List<Player> players) {
        validate(players);
        this.value = players;
    }

    private void validate(List<Player> players) {
        checkPlayerCount(players);
        checkDuplicatePlayer(players);
    }

    private void checkDuplicatePlayer(List<Player> players) {
        long distinctNamesCount = players.stream()
                .map(Player::getName)
                .distinct()
                .count();

        if (distinctNamesCount != players.size()) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다.");
        }
    }

    private void checkPlayerCount(List<Player> players) {
        if (players.size() < MIN_PLAYER_COUNT || players.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException(
                    String.format("최소 %d명 최대 %d명까지 입력받을 수 있습니다.", MIN_PLAYER_COUNT, MAX_PLAYER_COUNT));
        }
    }

    public List<Name> getNames() {
        return value.stream()
                .map(Participant::getName)
                .toList();
    }

    public List<Player> getValue() {
        return value;
    }
}

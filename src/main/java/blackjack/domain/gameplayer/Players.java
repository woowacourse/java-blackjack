package blackjack.domain.gameplayer;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {
    private static final int MAX_PLAYER_SIZE = 6;
    private static final String INVALID_PLAYER_SIZE_MESSAGE = "게임을 진행하는 플레이어의 수는 1명에서 6명 사이여야 합니다.";
    private static final String INVALID_DUPLICATED_NAME_MESSAGE = "게임을 진행하는 플레이어의 이름은 중복이 없어야합니다.";

    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<Player> players) {
        validatePlayersCount(players);
        validateDuplicatedPlayerNames(players);
    }

    private void validatePlayersCount(List<Player> players) {
        if (players.isEmpty() || players.size() > MAX_PLAYER_SIZE) {
            throw new IllegalArgumentException(INVALID_PLAYER_SIZE_MESSAGE);
        }
    }

    private void validateDuplicatedPlayerNames(List<Player> players) {
        if (hasDuplicatedNames(players)) {
            throw new IllegalArgumentException(INVALID_DUPLICATED_NAME_MESSAGE);
        }
    }

    private boolean hasDuplicatedNames(List<Player> players) {
        return getDistinctNameCount(players) != players.size();
    }

    private long getDistinctNameCount(List<Player> players) {
        return players.stream()
                .distinct()
                .count();
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Player::showName)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}

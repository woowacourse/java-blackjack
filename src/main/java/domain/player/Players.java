package domain.player;

import java.util.List;

public class Players {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 8;
    private static final String DEALER_NAME = "딜러";

    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<Player> players) {
        validateSize(players.size());
        validateDuplicatedNames(players);
        validateNotDealerName(players);
    }

    private void validateSize(int size) {
        if (size < MIN_SIZE || size > MAX_SIZE) {
            throw new IllegalArgumentException("[ERROR] 플레이어는 최소 " + MIN_SIZE + "명에서 최대 " + MAX_SIZE + "명까지 참여할 수 있습니다.");
        }
    }

    private void validateDuplicatedNames(List<Player> players) {
        long nonDuplicatedCount = players.stream()
                .map(Player::getPlayerName)
                .distinct()
                .count();
        if (nonDuplicatedCount != players.size()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 간 중복된 이름을 가질 수 없습니다.");
        }
    }

    private void validateNotDealerName(List<Player> players) {
        boolean isNoneMatchDealerName = players.stream()
                .map(Player::getPlayerName)
                .noneMatch(playerName -> playerName.equals(DEALER_NAME));

        if (!isNoneMatchDealerName) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 이름은 \"" + DEALER_NAME + "\"가 될 수 없습니다.");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}

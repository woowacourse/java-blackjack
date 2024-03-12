package blackjack.domain.participant;

import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<Player> players) {
        validateEntryNotEmpty(players);
        validateEachPlayerNameUnique(players);
    }

    private void validateEntryNotEmpty(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 플레이어가 없습니다");
        }
    }

    private void validateEachPlayerNameUnique(List<Player> players) {
        if (countUniquePlayer(players) != players.size()) {
            throw new IllegalArgumentException("[ERROR] 중복되는 플레이어의 이름이 존재합니다");
        }
    }

    private int countUniquePlayer(List<Player> players) {
        return (int) players.stream()
                .distinct()
                .count();
    }

    public static Players create(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(PlayerName::new)
                .map(Player::new)
                .toList();
        return new Players(players);
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}

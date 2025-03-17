package blackjack.domain.game;

import java.util.List;

public class Players {

    private static final int PLAYERS_VALID_SIZE = 6;

    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<Player> players) {
        if (players.size() > PLAYERS_VALID_SIZE) {
            throw new IllegalArgumentException("7명을 초과할 수 없습니다.");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getNamesOfParticipants() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }
}

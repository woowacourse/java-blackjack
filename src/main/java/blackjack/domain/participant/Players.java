package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        players = new ArrayList<>(players);
        validatePlayers(players);
        this.players = players;
    }

    private void validatePlayers(List<Player> players) {
        validateNull(players);
        validateSize(players);
        validateDuplicatedName(players);
    }

    private void validateNull(List<Player> players) {
        Objects.requireNonNull(players, "[ERROR] Players 를 null 로 생성할 수 없습니다.");
    }

    private void validateSize(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] Players 생성시 최소 한 명의 Player 가 필요합니다.");
        }
    }

    private void validateDuplicatedName(List<Player> players) {
        int nameCount = (int)players.stream()
            .map(Player::getName)
            .distinct()
            .count();
        if (nameCount < players.size()) {
            throw new IllegalArgumentException("[ERROR] Player 이름은 중복될 수 없습니다.");
        }
    }

    public List<Player> getValues() {
        return List.copyOf(players);
    }
}

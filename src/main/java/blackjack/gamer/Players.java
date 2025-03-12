package blackjack.gamer;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players() {
        this.players = new ArrayList<>();
    }

    public void addPlayersFrom(final String names) {
        validateEmpty(names);
        final List<String> parsedNames = List.of(names.replace(" ", "").split(","));
        validateNameCount(parsedNames);
        parsedNames.forEach(parsedName -> players.add(new Player(parsedName)));
    }

    private void validateEmpty(final String names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("비어있는 값을 입력했습니다. 다시 입력해주세요.");
        }
    }

    private void validateNameCount(final List<String> parsedNames) {
        if (parsedNames.size() >= 6) {
            throw new IllegalArgumentException("적정 인원을 초과했습니다. 다시 입력해주세요.");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}

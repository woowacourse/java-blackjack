package blackjack.gamer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {

    private static final int MAX_PLAYER_NUMBER = 6;

    private final List<Player> players;

    public Players() {
        this.players = new ArrayList<>();
    }

    public void addPlayersFrom(final String names) {
        validateEmpty(names);
        final List<String> parsedNames = List.of(names.replace(" ", "").split(","));
        validateDuplicate(parsedNames);
        validateNameCount(parsedNames);
        parsedNames.forEach(parsedName -> players.add(new Player(parsedName)));
    }

    private void validateEmpty(final String names) {
        if (names.isBlank()) {
            throw new IllegalArgumentException("비어있는 값을 입력했습니다. 다시 입력해주세요.");
        }
    }

    private void validateDuplicate(final List<String> parsedNames) {
        final Set<String> uniqueNames = new HashSet<>(parsedNames);
        if (uniqueNames.size() != parsedNames.size()) {
            throw new IllegalArgumentException("중복된 닉네임이 있습니다. 다시 입력해주세요.");
        }
    }

    private void validateNameCount(final List<String> parsedNames) {
        if (parsedNames.size() > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException("적정 인원을 초과했습니다. 다시 입력해주세요.");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}

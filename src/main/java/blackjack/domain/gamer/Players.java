package blackjack.domain.gamer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import blackjack.domain.result.Results;

public class Players {

    private static final String PLAYER_NAME_DUPLICATE_ERROR_MESSAGE = "참가자 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    public Players(List<Player> names) {
        validateDuplicate(names);
        players = names;
    }

    private void validateDuplicate(List<Player> names) {
        Set<Player> duplicateNames = new HashSet<>(names);

        if (names.size() != duplicateNames.size()) {
            throw new IllegalArgumentException(PLAYER_NAME_DUPLICATE_ERROR_MESSAGE);
        }
    }

    public void initGameResult(Map<Gamer, Results> gameResult) {
        players.forEach(player -> gameResult.put(player, new Results(new ArrayList<>())));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}

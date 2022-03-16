package blackjack.domain.gamer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import blackjack.domain.result.Results;

public class Players {

    private static final int MINIMUM_NUMBER = 2;
    private static final int MAXIMUM_NUMBER = 8;
    private static final String NUMBER_OF_PLAYERS_ERROR_MESSAGE = "2명에서 8명 까지만 참가 가능합니다.";
    private static final String PLAYER_NAME_DUPLICATE_ERROR_MESSAGE = "참가자 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    public Players(final List<Player> players) {
        validate(players);
        this.players = new ArrayList<>(players);
    }

    private void validate(List<Player> players) {
        validateNumberOfPlayers(players);
        validateDuplicate(players);
    }

    private void validateNumberOfPlayers(List<Player> players) {
        int number = players.size();

        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(NUMBER_OF_PLAYERS_ERROR_MESSAGE);
        }
    }

    private void validateDuplicate(final List<Player> players) {
        List<String> names = players.stream()
            .map(Player::getName)
            .collect(Collectors.toList());
        Set<String> duplicateNames = new HashSet<>(names);

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

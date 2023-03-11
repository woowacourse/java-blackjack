package blackjackgame.domain.user;

import blackjackgame.view.ErrorMessage;
import java.util.List;
import java.util.stream.Collectors;

public class Names {
    private static final int MAX_PLAYER_SIZE = 5;

    private final List<Name> names;

    public Names(List<String> playerNames) {
        validateNames(playerNames);

        names = playerNames.stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private void validateNames(List<String> playerNames) {
        validateDuplication(playerNames);
        validateSize(playerNames);
    }

    private void validateDuplication(List<String> playerNames) {
        if (hasDuplicatedName(playerNames)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_PLAYER_NAME_EXIST.getMessage());
        }
    }

    private boolean hasDuplicatedName(List<String> playerNames) {
        return playerNames.size() != playerNames.stream().distinct().count();
    }

    private void validateSize(List<String> players) {
        if (players.size() > MAX_PLAYER_SIZE || players.size() < 1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PLAYER_SIZE.getMessage());
        }
    }

    public List<Name> getNames() {
        return names;
    }
}

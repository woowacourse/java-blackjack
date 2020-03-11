package factory;

import domain.user.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerFactory {
    private static final String NAME_DELIMITER = ",";

    private PlayerFactory() {
    }

    public static List<Player> create(final String userInput) {
        List<Player> players = Arrays.stream(userInput.split(NAME_DELIMITER))
                .map(Player::new)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(players);
    }
}

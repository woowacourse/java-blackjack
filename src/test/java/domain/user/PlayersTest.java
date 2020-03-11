package domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayersTest {

    @ParameterizedTest
    @ValueSource(strings = {"pobi", "jason"})
    void create(String name) {
        String names = "pobi, jason";
        Players players = Players.of(names);
        List<String> playerNames = players.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.toList());

        assertThat(playerNames).contains(name);
    }
}
package blackjack.domain.participant;

import static blackjack.domain.participant.Players.PLAYERS_COUNT_ERROR_MESSAGE;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class PlayersTest {

    private final List<Player> playerData = new ArrayList<>(List.of(
            new Player("player1"),
            new Player("player2")
    ));
    private Players players;

    @BeforeEach
    void setUp() {
        this.players = new Players(playerData);
    }

    @ParameterizedTest(name = PLAYERS_COUNT_ERROR_MESSAGE + "{0}")
    @ValueSource(ints = {1, 9})
    void create_invalidCount(int playerCount) {
        List<Player> players = IntStream.range(0, playerCount)
                .mapToObj(x -> new Player(String.valueOf(playerCount)))
                .collect(toList());

        assertThatIllegalArgumentException().isThrownBy(
                () -> new Players(players)
        ).withMessage(PLAYERS_COUNT_ERROR_MESSAGE + players);
    }
}

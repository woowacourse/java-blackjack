package domain.gameplaying;

import static org.junit.jupiter.api.Assertions.*;

import domain.gameplaying.strategy.InfiniteDeck;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {

    private Players players;

    @BeforeEach
    void setUp() {
        this.players = Players.noOne(new InfiniteDeck());
    }

    @ParameterizedTest
    @MethodSource("playerNames")
    @DisplayName("플레이어들 중 현재(peek) 플레이어의 이름을 반환할 수 있어야한다.")
    void 현재_플레이어_이름_확인(List<String> names) {
        Players players = this.players.join(names);

        String expected = names.getFirst();
        String actual = players.currentPlayerName();

        assertEquals(expected, actual);
    }

    static Stream<List<String>> playerNames() {
        return Stream.of(
                List.of("pobi"),
                List.of("pobi", "jason"),
                List.of("pobi", "jason", "tars")
        );
    }
}

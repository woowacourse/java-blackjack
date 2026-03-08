package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {

    @ParameterizedTest
    @MethodSource("playerNames")
    @DisplayName("플레이어들 중 현재(peek) 플레이어의 이름을 반환할 수 있어야한다.")
    void 현재_플레이어_이름_확인(List<String> names) {
        Players players = new Players();
        names.forEach(players::add);

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

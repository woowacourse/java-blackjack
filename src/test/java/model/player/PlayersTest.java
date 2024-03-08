package model.player;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayersTest {

    @DisplayName("총 플레이어 수가 1명 이상이면 객체 생성 성공")
    @ParameterizedTest
    @MethodSource("provideValidPlayerNames")
    void testValidSizeOfPlayers(List<String> playerNames) {
        assertThatCode(() -> Players.from(playerNames)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> provideValidPlayerNames() {
        return Stream.of(
            Arguments.of(List.of("lily", "dora", "ella")),
            Arguments.of(List.of("lily", "jojo")),
            Arguments.of(List.of("lily"))
        );
    }

    @DisplayName("총 플레이어 수가 1명 미만이면 예외 발생")
    @Test
    void testInValidSizeOfPlayers() {
        List<String> playerNames = List.of();
        assertThatThrownBy(() -> Players.from(playerNames))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어 이름이 중복되면 예외 발생")
    @ParameterizedTest
    @MethodSource("provideDuplicatedPlayerNames")
    void testInvalidPlayerNamesUnique(List<String> names) {
        assertThatThrownBy(() -> Players.from(names))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> provideDuplicatedPlayerNames() {
        return Stream.of(
            Arguments.of(List.of("lily", "jojo", "lily")),
            Arguments.of(List.of("릴리", "조조", "조조"))
        );
    }
}

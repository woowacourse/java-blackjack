package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("provideForPlayerNamesDuplicatedExceptionTest")
    @DisplayName("플레이어명 중복 시 예외 발생")
    void PlayerNamesDuplicatedExceptionTest(final List<String> playerNames) {
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어명은 중복될 수 없습니다.");
    }

    private static Stream<Arguments> provideForPlayerNamesDuplicatedExceptionTest() {
        return Stream.of(
                Arguments.of(List.of("pobi", "pobi")),
                Arguments.of(List.of("pobi", "sun", "pobi"))
        );
    }
}

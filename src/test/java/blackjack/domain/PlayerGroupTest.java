package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerGroupTest {

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("invalidParameters")
    @DisplayName("플레이어그룹 생성 오류 테스트")
    void playerInvalidTest(List<Player> players, String testName) {
        assertThatThrownBy(() -> new PlayerGroup(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
                Arguments.of(List.of(), "플레이어 0명 입력"),
                Arguments.of(List.of(new Player("a"),
                        new Player("b"),
                        new Player("c"),
                        new Player("d"),
                        new Player("e"),
                        new Player("f"),
                        new Player("g"),
                        new Player("h"),
                        new Player("i")), "플레이어 9명 입력"),
                Arguments.of(List.of(new Player("pobi"),
                        new Player("ash"),
                        new Player("ash")), "플레이어 중복 입력")
        );
    }
}

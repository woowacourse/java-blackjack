package domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.result.GameResultStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

class GameResultStatusTest {

    @DisplayName("숫자 비교를 통해 결과를 알 수 있다.")
    @ParameterizedTest
    @MethodSource
    void resultOf(int standardTarget, int comparisonTarget, GameResultStatus expected) {
        assertThat(GameResultStatus.comparedTo(comparisonTarget, standardTarget)).isEqualTo(expected);
    }

    static Stream<Arguments> resultOf() {
        return Stream.of(
                Arguments.of(22, 22, PUSH),
                Arguments.of(22, 20, WIN),
                Arguments.of(20, 22, LOSE),
                Arguments.of(20, 15, LOSE),
                Arguments.of(15, 20, WIN),
                Arguments.of(20, 20, PUSH)
        );
    }

}

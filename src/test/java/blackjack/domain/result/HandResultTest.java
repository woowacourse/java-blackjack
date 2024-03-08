package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HandResultTest {
    @DisplayName("올바르게 반대되는 결과를 반환한다.")
    @ParameterizedTest
    @MethodSource("provideHandResultWithOpposite")
    void getOppositeTest(HandResult handResult, HandResult expectedOpposite) {
        assertThat(handResult.getOpposite()).isEqualTo(expectedOpposite);
    }

    private static Stream<Arguments> provideHandResultWithOpposite() {
        return Stream.of(
                Arguments.of(HandResult.WIN, HandResult.LOSE),
                Arguments.of(HandResult.LOSE, HandResult.WIN),
                Arguments.of(HandResult.DRAW, HandResult.DRAW)
        );
    }
}

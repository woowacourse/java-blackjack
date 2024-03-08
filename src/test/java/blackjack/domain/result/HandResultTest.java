package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class HandResultTest {
    private static Stream<Arguments> provideHandResultWithOppositeResult() {
        return Stream.of(
                Arguments.of(HandResult.WIN, HandResult.LOSE),
                Arguments.of(HandResult.DRAW, HandResult.DRAW),
                Arguments.of(HandResult.LOSE, HandResult.WIN)
        );
    }

    @DisplayName("입력된 결과와 반대되는 결과를 반환한다.")
    @ParameterizedTest
    @MethodSource("provideHandResultWithOppositeResult")
    void getOppositeTest(HandResult handResult, HandResult oppositeResult) {
        assertThat(handResult.getOpposite()).isEqualTo(oppositeResult);
    }
}

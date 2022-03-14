package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ResultTest {

    @ParameterizedTest(name = "[{index}] {0}의 반대는 {1}")
    @MethodSource("generateGetOppositeArguments")
    @DisplayName("반대 승패를 반환한다.")
    void getOpposite(Result input, Result output) {
        assertThat(input.getOpposite()).isEqualTo(output);
    }

    static Stream<Arguments> generateGetOppositeArguments() {
        return Stream.of(
                Arguments.of(Result.WIN, Result.LOSE),
                Arguments.of(Result.DRAW, Result.DRAW),
                Arguments.of(Result.LOSE, Result.WIN)
        );
    }
}

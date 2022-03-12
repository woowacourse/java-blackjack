package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ResultTest {

    @ParameterizedTest
    @MethodSource("parameters1")
    @DisplayName("참가자의 승패를 결정한다.")
    void getResult(int dealerScore, int participantScore, Result result) {
        assertThat(Result.decide(dealerScore, participantScore)).isEqualTo(result);
    }

    static Stream<Arguments> parameters1() {
        return Stream.of(
                // 딜러 21 이하
                Arguments.of(20, 21, Result.WIN),
                Arguments.of(20, 19, Result.LOSE),
                Arguments.of(20, 20, Result.DRAW),
                Arguments.of(20, 22, Result.LOSE), // 버스트

                // 딜러 21
                Arguments.of(21, 21, Result.DRAW),
                Arguments.of(21, 22, Result.LOSE), // 버스트
                Arguments.of(21, 16, Result.LOSE),

                // 딜러 21 이상(버스트)
                Arguments.of(22, 22, Result.LOSE), // 버스트
                Arguments.of(22, 20, Result.WIN));
    }

    @ParameterizedTest(name = "[{index}] {0}의 반대는 {1}")
    @MethodSource("generateInputAndOutput")
    @DisplayName("반대 승패를 반환한다.")
    void getOpposite(Result input, Result output) {
        assertThat(input.getOpposite()).isEqualTo(output);
    }

    static Stream<Arguments> generateInputAndOutput() {
        return Stream.of(
                Arguments.of(Result.WIN, Result.LOSE),
                Arguments.of(Result.DRAW, Result.DRAW),
                Arguments.of(Result.LOSE, Result.WIN)
        );
    }
}

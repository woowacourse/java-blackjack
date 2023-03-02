package domain.user;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ScoreCalculatorTest {
    @Test
    @DisplayName("카드 숫자 값들이 주어지면 단순 합산으로 점수를 계산할 수 있다.")
    void calculateScoreTest() {
        //given
        List<Integer> numbers = List.of(2, 3, 4, 5);
        ScoreCalculator scoreCalculator = new ScoreCalculator();

        //when
        int score = scoreCalculator.calculate(numbers);

        //then
        Assertions.assertThat(score).isEqualTo(14);
    }

    @ParameterizedTest
    @MethodSource("calculateScoreWithAceCase")
    @DisplayName("에이스를 포함한 합산 점수가 21 초과 시 에이스를 1점으로 계산한다.")
    void calculateScoreWithAceTest(List<Integer> numbers, int expected) {
        //given
        ScoreCalculator scoreCalculator = new ScoreCalculator();

        //when
        int score = scoreCalculator.calculate(numbers);

        //then
        Assertions.assertThat(score).isEqualTo(expected);
    }

    static Stream<Arguments> calculateScoreWithAceCase() {
        return Stream.of(
                Arguments.of(List.of(11, 7, 8), 16),
                Arguments.of(List.of(11, 11, 8), 20),
                Arguments.of(List.of(11, 11, 10, 9), 21)
        );
    }
}

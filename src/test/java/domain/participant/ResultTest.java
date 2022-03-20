package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ResultTest {

    @ParameterizedTest
    @MethodSource("provideScoreAndResult")
    @DisplayName("플레이어 점수와 딜러 점수에 따라 결과를 잘 반환하는지 확인한다.")
    void judgeResultTest(List<Integer> scores, List<Boolean> isBlackJack, Result result) {
        assertThat(Result.judgeResult(scores.get(0), scores.get(1), isBlackJack.get(0),
            isBlackJack.get(1))).isEqualTo(result);
    }

    private static Stream<Arguments> provideScoreAndResult() {
        return Stream.of(
            Arguments.of(List.of(18, 17), List.of(false, false), Result.WIN),
            Arguments.of(List.of(18, 18), List.of(false, false), Result.PUSH),
            Arguments.of(List.of(17, 18), List.of(false, false), Result.LOSE),
            Arguments.of(List.of(23, 21), List.of(false, false), Result.LOSE),
            Arguments.of(List.of(20, 23), List.of(false, false), Result.WIN),
            Arguments.of(List.of(21, 21), List.of(true, false), Result.BLACKJACK),
            Arguments.of(List.of(21, 21), List.of(false, true), Result.LOSE),
            Arguments.of(List.of(24, 22), List.of(false, false), Result.LOSE));

    }
}

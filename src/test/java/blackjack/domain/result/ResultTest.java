package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ResultTest {

    @ParameterizedTest(name = "{index} {displayName} userScore = {0}, dealerScore = {1}, result = {2}")
    @MethodSource("provideScoreAndExpected")
    @DisplayName("유저와 딜러의 점수에 따라 결과가 올바르게 나온다.")
    void checkUserResultTest(final int userScore, final int dealerScore, final Result expected) {
        assertThat(Result.checkUserResult(userScore, dealerScore)).isEqualTo(expected);
    }

    static Stream<Arguments> provideScoreAndExpected() {
        return Stream.of(
                Arguments.of(22,22, Result.LOSE),
                Arguments.of(21,22,Result.WIN),
                Arguments.of(22,21,Result.LOSE),
                Arguments.of(20,21,Result.LOSE),
                Arguments.of(21,20,Result.WIN),
                Arguments.of(21,21,Result.DRAW)
        );
    }
}

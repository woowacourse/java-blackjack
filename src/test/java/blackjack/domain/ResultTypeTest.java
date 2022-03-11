package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResultTypeTest {

    @ParameterizedTest(name = "유저의 점수가 {1} 이고 딜러의 점수가 {2} 이면, 유저의 결과는 {0} 이다.")
    @MethodSource("provideScoreAndResult")
    @DisplayName("유저의 승무패를 계산한다.")
    void findUserResult(ResultType resultType, int userScore, int dealerScore) {
        final ResultType actual = ResultType.findUserResult(userScore, dealerScore);

        assertThat(actual).isEqualTo(resultType);
    }

    static Stream<Arguments> provideScoreAndResult() {
        return Stream.of(
                Arguments.of(ResultType.DRAW, 21, 21),
                Arguments.of(ResultType.DRAW, 23, 22),
                Arguments.of(ResultType.DRAW, 22, 23),
                Arguments.of(ResultType.WIN, 21, 20),
                Arguments.of(ResultType.WIN, 21, 22),
                Arguments.of(ResultType.LOSE, 20, 21),
                Arguments.of(ResultType.LOSE, 22, 21)
        );
    }

    @ParameterizedTest(name = "유저의 결과가 {0} 이라면, 딜러의 결과는 {1} 이다.")
    @MethodSource("provideResultsOfUserAndDealer")
    @DisplayName("딜러의 결과를 계산한다.")
    void findDealerResult(ResultType userResult, ResultType dealerResult) {
        final ResultType actual = ResultType.findDealerResult(userResult);

        assertThat(actual).isEqualTo(dealerResult);
    }

    static Stream<Arguments> provideResultsOfUserAndDealer() {
        return Stream.of(
                Arguments.of(ResultType.WIN, ResultType.LOSE),
                Arguments.of(ResultType.LOSE, ResultType.WIN),
                Arguments.of(ResultType.DRAW, ResultType.DRAW)
        );
    }
}

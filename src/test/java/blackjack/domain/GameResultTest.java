package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTest {

    @ParameterizedTest(name = "유저의 점수가 {1} 이고 딜러의 점수가 {2} 이면, 유저의 결과는 {0} 이다.")
    @MethodSource("provideScoreAndResult")
    @DisplayName("유저의 승무패를 계산한다.")
    void findUserResult(GameResult gameResult, int userScore, int dealerScore) {
        final GameResult actual = GameResult.findUserResult(userScore, dealerScore);

        assertThat(actual).isEqualTo(gameResult);
    }

    static Stream<Arguments> provideScoreAndResult() {
        return Stream.of(
                Arguments.of(GameResult.DRAW, 21, 21),
                Arguments.of(GameResult.DRAW, 23, 22),
                Arguments.of(GameResult.DRAW, 22, 23),
                Arguments.of(GameResult.WIN, 21, 20),
                Arguments.of(GameResult.WIN, 21, 22),
                Arguments.of(GameResult.LOSE, 20, 21),
                Arguments.of(GameResult.LOSE, 22, 21)
        );
    }

    @ParameterizedTest(name = "유저의 결과가 {0} 이라면, 딜러의 결과는 {1} 이다.")
    @MethodSource("provideResultsOfUserAndDealer")
    @DisplayName("딜러의 결과를 계산한다.")
    void findDealerResult(GameResult userResult, GameResult dealerResult) {
        final GameResult actual = GameResult.findDealerResult(userResult);

        assertThat(actual).isEqualTo(dealerResult);
    }

    static Stream<Arguments> provideResultsOfUserAndDealer() {
        return Stream.of(
                Arguments.of(GameResult.WIN, GameResult.LOSE),
                Arguments.of(GameResult.LOSE, GameResult.WIN),
                Arguments.of(GameResult.DRAW, GameResult.DRAW)
        );
    }
}

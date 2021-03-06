package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @DisplayName("블랙잭 게임 결과를 비교하여 승패를 가른다")
    @ParameterizedTest
    @MethodSource
    void test_value_of(int playerScore, int dealerScore, GameResult actual) {
        //when
        GameResult gameResult = GameResult.valueOf(playerScore, dealerScore);

        //then
        assertThat(gameResult).isEqualTo(actual);
    }

    private static Stream<Arguments> test_value_of() {
        return Stream.of(
                Arguments.of(20, 21, GameResult.LOSE),
                Arguments.of(21, 20, GameResult.WIN),
                Arguments.of(20, 20, GameResult.DRAW),
                Arguments.of(22, 20, GameResult.LOSE),
                Arguments.of(20, 22, GameResult.WIN),
                Arguments.of(22, 22, GameResult.LOSE)
        );
    }

    @DisplayName("승패 결과를 반대로 반환한다")
    @ParameterizedTest
    @MethodSource
    void test_reverse(GameResult gameResult, GameResult expected) {
        //when
        GameResult actual = gameResult.reverse();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> test_reverse() {
        return Stream.of(
                Arguments.of(GameResult.WIN, GameResult.LOSE),
                Arguments.of(GameResult.DRAW, GameResult.DRAW),
                Arguments.of(GameResult.LOSE, GameResult.WIN)
        );
    }
}

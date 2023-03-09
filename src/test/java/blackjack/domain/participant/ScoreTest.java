package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ScoreTest {

    @ParameterizedTest(name = "scoreValue={0}")
    @ValueSource(ints = {-1, -2, -3})
    @DisplayName("0보다 작은 점수는 예외를 발생한다")
    void lessThanZeroException(int value) {
        assertThatThrownBy(() -> new Score(value)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "scoreValueA={0}, scoreValueB={1}, expectedValue={2}")
    @MethodSource("increaseScoreDummy")
    @DisplayName("점수를 더한다.")
    void increase(int valueA, int valueB, int expectedValue) {
        Score scoreA = new Score(valueA);
        Score scoreB = new Score(valueB);

        Score result = scoreA.increase(scoreB);
        Score expectedScore = new Score(expectedValue);
        assertThat(result).isEqualTo(expectedScore);
    }

    static Stream<Arguments> increaseScoreDummy() {
        return Stream.of(
                Arguments.of(1, 2, 3),
                Arguments.of(4, 5, 9),
                Arguments.of(10, 11, 21),
                Arguments.of(20, 3, 23)
        );
    }

    @ParameterizedTest(name = "scoreValueA={0}, scoreValueB={1}, expectedLessThan={2}, expectedGreaterThan={3}")
    @MethodSource("compareScoreDummy")
    @DisplayName("점수를 비교한다")
    void compareScore(int value, int otherValue, boolean expectedLessThan, boolean expectedGreaterThan) {
        Score score = new Score(value);
        Score other = new Score(otherValue);

        boolean isLessThan = score.isLessThan(other);
        boolean isGreaterThan = score.isGreaterThan(other);

        assertAll(
                () -> assertThat(isLessThan).isEqualTo(expectedLessThan),
                () -> assertThat(isGreaterThan).isEqualTo(expectedGreaterThan)
        );
    }

    static Stream<Arguments> compareScoreDummy() {
        return Stream.of(
                Arguments.of(1, 2, true, false),
                Arguments.of(4, 4, false, false),
                Arguments.of(21, 11, false, true)
        );
    }

    @ParameterizedTest(name = "scoreValue={0}, aceCount={1}, expectedValue={2}")
    @MethodSource("aceScoreDummy")
    @DisplayName("ACE를 최적의 점수로 계산한다")
    void calculateBestScoreAce(int value, int aceCount, int expectedValue) {
        Score beforeScore = new Score(value);
        Score expectedScore = new Score(expectedValue);

        Score afterScore = beforeScore.calculateBestScoreAce(aceCount);

        assertThat(afterScore).isEqualTo(expectedScore);
    }

    static Stream<Arguments> aceScoreDummy() {
        return Stream.of(
                Arguments.of(44, 4, 14),
                Arguments.of(33, 3, 13),
                Arguments.of(22, 2, 12),
                Arguments.of(11, 1, 11)
        );
    }
}

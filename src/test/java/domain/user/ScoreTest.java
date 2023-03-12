package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("블랙잭 카드 점수는 ")
class ScoreTest {
    @Test
    @DisplayName("카드의 denomination 숫자 값을 이용해 계산한다.")
    void createTest() {
        assertDoesNotThrow(() -> new Score(5));
    }

    @Test
    @DisplayName("합산할 수 있다.")
    void addTest() {
        Score score1 = new Score(4);
        Score score2 = new Score(6);

        Score sumResult = score1.add(score2);

        assertThat(sumResult).isEqualTo(new Score(10));
    }

    @ParameterizedTest
    @CsvSource(value = {"5,6", "6,6"})
    @DisplayName("주어진 점수 이하인지 판단 가능하다.")
    void isLessThanOrEqualTest(int scoreValue1, int scoreValue2) {
        Score score1 = new Score(scoreValue1);
        Score score2 = new Score(scoreValue2);

        assertThat(score1.isLessThanOrEqual(score2)).isTrue();
    }

    @Test
    @DisplayName("대소 비교가 가능하다.")
    void isLessThanTest() {
        Score score1 = new Score(4);
        Score score2 = new Score(6);

        assertThat(score1.isLessThan(score2)).isTrue();
    }

    @Test
    @DisplayName("21점 초과인지 판단할 수 있다.")
    void isOverMax() {
        Score score1 = new Score(22);
        assertThat(score1.isOverMax()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("calculateScoreWithAceCases")
    @DisplayName("Ace를 1혹은 11로 계산한다.")
    void calculateAceAsOne(int scoreInitSum, int aceCount, int expected) {
        Score score = new Score(scoreInitSum);

        assertThat(score.calculateAceAsOne(aceCount)).isEqualTo(new Score(expected));
    }

    static Stream<Arguments> calculateScoreWithAceCases() {
        return Stream.of(
                Arguments.of(13, 1, 13), // Ace 2
                Arguments.of(41, 2, 21), // 10 9 Ace Ace
                Arguments.of(21, 1, 21), // Ace 10
                Arguments.of(22, 2, 12), // Ace Ace
                Arguments.of(44, 3, 14)  // Ace Ace Ace Ace
        );
    }
}

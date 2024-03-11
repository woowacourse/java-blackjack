package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {
    @Test
    @DisplayName("음수는 점수로 사용될 수 없다.")
    void negativeScoreTest() {
        int negativeValue = -1;
        assertThatThrownBy(() -> Score.of(negativeValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수는 점수로 사용될 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"21, false", "22, true"})
    @DisplayName("점수가 21점을 초과하면 버스트")
    void burstIfExceed21Test(int value, boolean expected) {
        Score score = Score.of(value);

        assertThat(score.isBusted()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {20, 22})
    @DisplayName("21점이 아닌 점수가 블랙잭 상태와 함께 전달되는 경우 예외가 발생한다.")
    void throwExceptionWhenBlackJackWithNot21Test(int notBlackJack) {
        boolean isBlackJack = true;
        assertThatThrownBy(() -> new Score(notBlackJack, isBlackJack))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("21점이 아닐 경우 블랙잭일 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({
            "21, 20, true",
            "20, 20, false",
            "19, 20, false",
    })
    @DisplayName("현재 점수가 다른 점수보다 더 높은지 확인할 수 있다.")
    void isGreaterThenTest(int currentValue, int relativeValue, boolean expected) {
        Score currentScore = Score.of(currentValue);
        Score relativeScore = Score.of(relativeValue);

        boolean actual = currentScore.isGreaterThan(relativeScore);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "21, 20, false",
            "20, 20, true",
            "19, 20, false",
    })
    @DisplayName("현재 점수가 다른 점수보다 더 높은지 확인할 수 있다.")
    void isSameTest(int currentValue, int relativeValue, boolean expected) {
        Score currentScore = Score.of(currentValue);
        Score relativeScore = Score.of(relativeValue);

        boolean actual = currentScore.isSame(relativeScore);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "21, 20, false",
            "20, 20, false",
            "19, 20, true",
    })
    @DisplayName("현재 점수가 다른 점수보다 더 높은지 확인할 수 있다.")
    void isLessThanTest(int currentValue, int relativeValue, boolean expected) {
        Score currentScore = Score.of(currentValue);
        Score relativeScore = Score.of(relativeValue);

        boolean actual = currentScore.isLessThan(relativeScore);
        assertThat(actual).isEqualTo(expected);
    }
}

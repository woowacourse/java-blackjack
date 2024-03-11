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
            "1, 22",
            "20, 19",
    })
    @DisplayName("상대가 버스트이거나, 현재 점수가 상대 점수보다 더 높다면 승리한다.")
    void winTest(int currentValue, int relativeValue) {
        Score currentScore = Score.of(currentValue);
        Score relativeScore = Score.of(relativeValue);

        GameResult actual = currentScore.compareWith(relativeScore);
        assertThat(actual).isEqualTo(GameResult.WIN);
    }

    @ParameterizedTest
    @CsvSource({
            "22, 1",
            "19, 20",
    })
    @DisplayName("현재 점수가 버스트이거나 현재 점수가 상대 점수보다 낮다면 패배한다.")
    void loseTest(int currentValue, int relativeValue) {
        Score currentScore = Score.of(currentValue);
        Score relativeScore = Score.of(relativeValue);

        GameResult actual = currentScore.compareWith(relativeScore);
        assertThat(actual).isEqualTo(GameResult.LOSE);
    }

    @ParameterizedTest
    @CsvSource({
            "18, 18",
            "20, 20",
    })
    @DisplayName("둘 다 버스트가 아니고, 점수가 같다면 무승부이다.")
    void drawTest(int currentValue, int relativeValue) {
        Score currentScore = Score.of(currentValue);
        Score relativeScore = Score.of(relativeValue);

        GameResult actual = currentScore.compareWith(relativeScore);
        assertThat(actual).isEqualTo(GameResult.DRAW);
    }

    @ParameterizedTest
    @CsvSource({
            "22, 23",
            "30, 25",
    })
    @DisplayName("둘 다 버스트인 경우 BOTH_BUSTED 상태로 판단한다.")
    void bothBustedTest(int currentValue, int relativeValue) {
        Score currentScore = Score.of(currentValue);
        Score relativeScore = Score.of(relativeValue);

        GameResult actual = currentScore.compareWith(relativeScore);
        assertThat(actual).isEqualTo(GameResult.BOTH_BUSTED);
    }

    @Test
    @DisplayName("둘 다 블랙잭인 경우 DRAW 상태로 판단한다.")
    void bothBlackJackTest() {
        Score currentScore = Score.blackJackScore();
        Score relativeScore = Score.blackJackScore();

        GameResult actual = currentScore.compareWith(relativeScore);
        assertThat(actual).isEqualTo(GameResult.DRAW);
    }
}

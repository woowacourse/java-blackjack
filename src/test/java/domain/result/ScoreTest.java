package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScoreTest {

    @DisplayName("점수가 21을 초과하면 Bust다.")
    @ParameterizedTest
    @CsvSource({"22, true", "21, false", "20, false"})
    void isBust(int scoreValue, boolean expected) {
        Score score = new Score(scoreValue);
        assertThat(score.isBust()).isEqualTo(expected);
    }

    @DisplayName("점수가 정확히 21이면 Blackjack이다.")
    @ParameterizedTest
    @CsvSource({"21, true", "20, false", "22, false"})
    void isBlackjack(int scoreValue, boolean expected) {
        Score score = new Score(scoreValue);
        assertThat(score.isBlackjack()).isEqualTo(expected);
    }

    @DisplayName("자신의 점수가 다른 Score의 점수보다 큰지 판별한다.")
    @Test
    void isGreaterThan() {
        Score score20 = new Score(20);
        Score score19 = new Score(19);

        assertThat(score20.isGreaterThan(score19)).isTrue();
    }

    @DisplayName("자신의 점수가 다른 Score의 점수보다 작은지 판별한다.")
    @Test
    void isLessThan() {
        Score score19 = new Score(19);
        Score score20 = new Score(20);
        assertThat(score19.isLessThan(score20)).isTrue();
    }
}

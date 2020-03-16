package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ScoreTest {

    @DisplayName("score나 count가 0이하인 경우 예외처리")
    @ParameterizedTest
    @CsvSource(value = {"0, 1", "1, 0", "0, 0", "-1, 1", "1, -1", "-1, -1"})
    void isNotNative(int score, int count) {
        assertThatThrownBy(() -> new Score(score, false, count))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("1");
    }

    @DisplayName("카드 점수가 21을 넘는지 확인")
    @Test
    void isOverBlackJack() {
        assertThat((new Score(21)).isBust()).isFalse();
        assertThat((new Score(22)).isBust()).isTrue();
    }

    @DisplayName("카드가 블랙잭인지 확인")
    @Test
    void isBlackJack() {
        assertThat((new Score(21, false, 2)).isBlackJack()).isTrue();
        assertThat((new Score(11, true, 2)).isBlackJack()).isTrue();

        assertThat((new Score(21)).isBlackJack()).isFalse();
        assertThat((new Score(21)).isBlackJack()).isFalse();
        assertThat((new Score(20, false, 2)).isBlackJack()).isFalse();
        assertThat((new Score(22, false, 2)).isBlackJack()).isFalse();
    }

    @DisplayName("equals 확인")
    @Test
    void isSame() {
        Score scoreBlackJack = new Score(21, false, 2);
        Score scoreBlackJackToo = new Score(21, false,2);
        Score scoreBust = new Score(22);
        Score scoreBustToo = new Score(22);
        Score scoreTwentyOne = new Score(21);

        assertThat(scoreBlackJack.equals(scoreBlackJackToo)).isTrue();
        assertThat(scoreBust.equals(scoreBustToo)).isTrue();
        assertThat(scoreBlackJack.equals(scoreBust)).isFalse();
        assertThat(scoreBlackJack.equals(scoreTwentyOne)).isFalse();
    }
}

package blackjack.domain.hand;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("점수가 21 초과이면 버스트이다")
    void isBust_returnsTrue_whenScoreExceedsTwentyOne() {
        assertThat(new Score(22).isBust()).isTrue();
    }

    @Test
    @DisplayName("점수가 21 이하이면 버스트가 아니다")
    void isBust_returnsFalse_whenScoreIsEqualToTwentyOne() {
        assertThat(new Score(21).isBust()).isFalse();
    }

    @Test
    @DisplayName("점수가 다른 점수보다 크면 참이다")
    void isGreaterThan_returnsTrue_whenScoreIsGreater() {
        assertThat(new Score(20).isGreaterThan(new Score(18))).isTrue();
    }

    @Test
    @DisplayName("점수가 다른 점수보다 작으면 isGreaterThan이 거짓이다")
    void isGreaterThan_returnsFalse_whenScoreIsSmaller() {
        assertThat(new Score(18).isGreaterThan(new Score(20))).isFalse();
    }

    @Test
    @DisplayName("점수가 다른 점수보다 작으면 참이다")
    void isLessThan_returnsTrue_whenScoreIsSmaller() {
        assertThat(new Score(18).isLessThan(new Score(20))).isTrue();
    }

    @Test
    @DisplayName("점수가 다른 점수와 같으면 isLessThanOrEqualTo가 참이다")
    void isLessThanOrEqualTo_returnsTrue_whenScoresAreEqual() {
        assertThat(new Score(16).isLessThanOrEqualTo(new Score(16))).isTrue();
    }

    @Test
    @DisplayName("점수가 다른 점수보다 크면 isLessThanOrEqualTo가 거짓이다")
    void isLessThanOrEqualTo_returnsFalse_whenScoreIsGreater() {
        assertThat(new Score(17).isLessThanOrEqualTo(new Score(16))).isFalse();
    }
}

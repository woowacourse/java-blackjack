package blackjack.domain.hand;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("점수가 21 초과이면 버스트이다")
    void isBust_returnsTrue_whenScoreExceedsTwentyOne() {
        // given & when
        boolean result = new Score(22).isBust();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("점수가 21 이하이면 버스트가 아니다")
    void isBust_returnsFalse_whenScoreIsEqualToTwentyOne() {
        // given & when
        boolean result = new Score(21).isBust();

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("점수가 다른 점수보다 크면 참이다")
    void isGreaterThan_returnsTrue_whenScoreIsGreater() {
        // given
        Score score = new Score(20);

        // when
        boolean result = score.isGreaterThan(new Score(18));

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("점수가 다른 점수보다 작으면 isGreaterThan이 거짓이다")
    void isGreaterThan_returnsFalse_whenScoreIsSmaller() {
        // given
        Score score = new Score(18);

        // when
        boolean result = score.isGreaterThan(new Score(20));

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("점수가 다른 점수보다 작으면 참이다")
    void isLessThan_returnsTrue_whenScoreIsSmaller() {
        // given
        Score score = new Score(18);

        // when
        boolean result = score.isLessThan(new Score(20));

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("점수가 다른 점수와 같으면 isLessThanOrEqualTo가 참이다")
    void isLessThanOrEqualTo_returnsTrue_whenScoresAreEqual() {
        // given
        Score score = new Score(16);

        // when
        boolean result = score.isLessThanOrEqualTo(new Score(16));

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("점수가 다른 점수보다 크면 isLessThanOrEqualTo가 거짓이다")
    void isLessThanOrEqualTo_returnsFalse_whenScoreIsGreater() {
        // given
        Score score = new Score(17);

        // when
        boolean result = score.isLessThanOrEqualTo(new Score(16));

        // then
        assertThat(result).isFalse();
    }
}

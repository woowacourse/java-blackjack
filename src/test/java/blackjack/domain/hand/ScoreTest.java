package blackjack.domain.hand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {

    @DisplayName("점수는 음수일 수 없다.")
    @Test
    void testCreateNegativeScore() {
        // given
        int value = -1;

        // when & then
        assertThatThrownBy(() -> new Score(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("점수를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void testCreateScore(int value) {
        // when & then
        assertThatCode(() -> new Score(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("점수끼리 더한다.")
    @Test
    void testAddScore() {
        // given
        Score score = new Score(1);

        // when
        Score actual = score.add(new Score(2));

        // then
        assertThat(actual).isEqualTo(new Score(3));
    }

    @DisplayName("점수에 값을 더한다.")
    @Test
    void testAddValue() {
        // given
        Score score = new Score(1);

        // when
        Score actual = score.add(2);

        // then
        assertThat(actual).isEqualTo(new Score(3));
    }

    @DisplayName("21이면 블랙잭 점수다.")
    @Test
    void testIsBlackjack() {
        // given
        Score score = new Score(21);

        // when
        boolean actual = score.isBlackjack();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("21이 아니면 블랙잭 점수가 아니다.")
    @ParameterizedTest
    @ValueSource(ints = {20, 22})
    void testIsNotBlackjack(int value) {
        // given
        Score score = new Score(value);

        // when
        boolean actual = score.isBlackjack();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("21을 초과하면 버스트 점수다.")
    @Test
    void testIsBust() {
        // given
        Score score = new Score(22);

        // when
        boolean actual = score.isBust();

        // when & then
        assertThat(actual).isTrue();
    }

    @DisplayName("21을 초과하지 않으면 버스트 점수가 아니다.")
    @Test
    void testIsNotBust() {
        // given
        Score score = new Score(21);

        // when
        boolean actual = score.isBust();

        // when & then
        assertThat(actual).isFalse();
    }

    @DisplayName("21을 넘지 않을 경우 플레이어 히트 가능 점수다.")
    @ParameterizedTest
    @ValueSource(ints = {21, 20})
    void testIsPlayerHit(int value) {
        // given
        Score score = new Score(value);

        // when
        boolean actual = score.isPlayerHit();

        // when & then
        assertThat(actual).isTrue();
    }

    @DisplayName("21을 넘으면 플레이어 히트 가능 점수가 아니다.")
    @Test
    void testIsNotPlayerHit() {
        // given
        Score score = new Score(22);

        // when
        boolean actual = score.isPlayerHit();

        // when & then
        assertThat(actual).isFalse();
    }

    @DisplayName("16을 넘지 않을 경우 딜러 히트 가능 점수다.")
    @ParameterizedTest
    @ValueSource(ints = {16, 15})
    void testIsDealerHit(int value) {
        // given
        Score score = new Score(value);

        // when
        boolean actual = score.isDealerHit();

        // when & then
        assertThat(actual).isTrue();
    }

    @DisplayName("16을 넘으면 딜러 히트 가능 점수가 아니다.")
    @Test
    void testIsNotDealerHit() {
        // given
        Score score = new Score(17);

        // when
        boolean actual = score.isDealerHit();

        // when & then
        assertThat(actual).isFalse();
    }

    @DisplayName("주어진 점수보다 큰지 판별한다.")
    @ParameterizedTest
    @CsvSource(value = {"1, true", "2, false", "3, false"})
    void testIsBiggerThan(int value, boolean expected) {
        // given
        Score score = new Score(2);

        // when
        boolean actual = score.isBiggerThan(new Score(value));

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("주어진 점수보다 작은지 판별한다.")
    @ParameterizedTest
    @CsvSource(value = {"1, false", "2, false", "3, true"})
    void testIsSmallerThan(int value, boolean expected) {
        // given
        Score score = new Score(2);

        // when
        boolean actual = score.isLessThan(new Score(value));

        // then
        assertThat(actual).isEqualTo(expected);
    }
}

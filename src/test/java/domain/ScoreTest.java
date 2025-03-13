package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {
    @DisplayName("21이 넘는 경우는 bust로 판단한다.")
    @ParameterizedTest
    @ValueSource(ints = {22, 23, 24, 25, 26, 27, 28, 29, 30})
    void upper_21_bust(int value) {
        // given
        Score score = new Score(value);

        // when
        final boolean actual = score.isBust();

        //then
        assertThat(actual).isTrue();
    }

    @DisplayName("21이 이하인 경우는 bust로 판단하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21})
    void lower_21_not_bust(int value) {
        // given
        Score score = new Score(value);

        // when
        final boolean actual = score.isBust();

        //then
        assertThat(actual).isFalse();
    }

    @DisplayName("21인 경우 HIT로 판단한다.")
    @Test
    void _21_is_hit() {
        // given
        Score score = new Score(21);

        // when
        final boolean actual = score.isHit();

        //then
        assertThat(actual).isTrue();
    }

    @DisplayName("21이 아닌 경우 HIT로 판단하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
            22, 23, 24, 25, 26, 27, 28, 29, 30
    })
    void not_21_is_not_hit(int value) {
        // given
        Score score = new Score(value);

        // when
        final boolean actual = score.isHit();

        //then
        assertThat(actual).isFalse();
    }
}

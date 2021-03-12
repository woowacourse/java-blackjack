package blakcjack.domain.score;

import blakcjack.exception.NegativeNumericException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blakcjack.domain.score.Score.MAX_CACHE_VALUE;
import static blakcjack.domain.score.Score.MIN_CACHE_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {
    @DisplayName("캐싱 확인")
    @Test
    void cache() {
        for (int value = MIN_CACHE_VALUE; value <= MAX_CACHE_VALUE; value++) {
            assertThat(Score.from(value))
                    .isSameAs(Score.from(value));
        }
    }

    @DisplayName("캐시에 없으면 새로운 객체가 생성된다.")
    @Test
    void create() {
        final Score score = Score.from(40);
        assertThat(score).isEqualTo(Score.from(40));
    }

    @DisplayName("음수가 입력되면 예외 발생")
    @Test
    void create_negative_throw() {
        assertThatThrownBy(() -> Score.from(-1))
                .isInstanceOf(NegativeNumericException.class);
    }

    @DisplayName("Bust 되지 않을시 에이스를 11로 선택한다.")
    @Test
    void applyAsHigherAce() {
        assertThat(Score.from(11).applyAsHigherAce())
                .isEqualTo(Score.from(21));

        assertThat(Score.from(12).applyAsHigherAce())
                .isEqualTo(Score.from(12));
    }

    @DisplayName("현재 점수가 더 큰 경우")
    @Test
    void isHigherThan() {
        final Score score = Score.from(10);
        assertThat(score.isHigherThan(Score.from(9)))
                .isTrue();
    }

    @DisplayName("현재 점수가 더 작은 경우")
    @Test
    void isLowerThan() {
        final Score score = Score.from(9);
        assertThat(score.isLowerThan(Score.from(10)))
                .isTrue();
    }

    @DisplayName("21보다 작은지 큰지")
    @Test
    void isLowerThanBlackJackValue() {
        assertThat(Score.from(20)
                .isLowerThanBlackJackValue())
                .isTrue();

        assertThat(Score.from(22)
                .isLowerThanBlackJackValue())
                .isFalse();
    }
}

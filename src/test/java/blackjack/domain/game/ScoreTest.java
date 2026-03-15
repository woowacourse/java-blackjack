package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ScoreTest {
    private static final int LOWER_SCORE_VALUE = 10;
    private static final int HIGHER_SCORE_VALUE = 20;

    @Nested
    @DisplayName("객체의 점수가 특정 점수 보다 큰지 판단한다")
    class IsBiggerThan {
        @Test
        void 객체의_점수가_특정_점수보다_크면_TRUE를_반환한다() {
            // given
            Score score = new Score(HIGHER_SCORE_VALUE);
            // when & then
            assertThat(score.isBiggerThan(LOWER_SCORE_VALUE)).isTrue();
        }

        @Test
        void 객체의_점수가_특정_점수보다_작거나_같으면_FALSE를_반환한다() {
            // given
            Score score = new Score(LOWER_SCORE_VALUE);
            // when & then
            assertThat(score.isBiggerThan(HIGHER_SCORE_VALUE)).isFalse();
        }
    }

    @Nested
    @DisplayName("객체의 점수가 특정 점수와 같거나 작은지 판단한다")
    class isLessThanOrEqual {
        @Test
        void 객체의_점수가_특정_점수보다_작거나_같으면_TRUE를_반환한다() {
            // given
            Score score = new Score(LOWER_SCORE_VALUE);
            // when & then
            assertThat(score.isLessThanOrEqual(HIGHER_SCORE_VALUE)).isTrue();
        }

        @Test
        void 객체의_점수가_특정_점수보다_크면_FALSE를_반환한다() {
            // given
            Score score = new Score(HIGHER_SCORE_VALUE);
            // when & then
            assertThat(score.isLessThanOrEqual(LOWER_SCORE_VALUE)).isFalse();
        }
    }
}
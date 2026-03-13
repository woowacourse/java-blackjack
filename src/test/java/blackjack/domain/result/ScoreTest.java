package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScoreTest {
    private static final int LOWER_SCORE_VALUE = 10;
    private static final int HIGHER_SCORE_VALUE = 20;

    @Test
    void 객체의_점수가_특정_점수_보다_크면_TRUE_를_반환한다() {
        // given
        Score score = new Score(HIGHER_SCORE_VALUE);
        // when & then
        assertThat(score.isBiggerThan(LOWER_SCORE_VALUE)).isTrue();
    }

    @Test
    void 객체의_점수가_특정_점수_보다_작거나_같으면_FALSE_를_반환한다() {
        // given
        Score score = new Score(LOWER_SCORE_VALUE);
        // when & then
        assertThat(score.isBiggerThan(HIGHER_SCORE_VALUE)).isFalse();
    }
}
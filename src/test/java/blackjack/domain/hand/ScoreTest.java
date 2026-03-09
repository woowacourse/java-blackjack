package blackjack.domain.hand;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    void 점수가_21_초과이면_버스트이다() {
        assertThat(new Score(22).isBust()).isTrue();
    }

    @Test
    void 점수가_21_이하이면_버스트가_아니다() {
        assertThat(new Score(21).isBust()).isFalse();
    }

    @Test
    void 점수가_더_크면_compareTo가_양수이다() {
        Score higher = new Score(20);
        Score lower = new Score(18);

        assertThat(higher.compareTo(lower)).isPositive();
    }

    @Test
    void 점수가_더_작으면_compareTo가_음수이다() {
        Score lower = new Score(18);
        Score higher = new Score(20);

        assertThat(lower.compareTo(higher)).isNegative();
    }

    @Test
    void 점수가_같으면_compareTo가_0이다() {
        Score score = new Score(20);
        Score same = new Score(20);

        assertThat(score.compareTo(same)).isZero();
    }
}

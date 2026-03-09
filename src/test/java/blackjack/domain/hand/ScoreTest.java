package blackjack.domain.hand;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

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
    void 점수가_다른_점수보다_크면_참이다() {
        assertThat(new Score(20).isGreaterThan(new Score(18))).isTrue();
    }

    @Test
    void isGreaterThan은_점수가_다른_점수보다_작으면_거짓이다() {
        assertThat(new Score(18).isGreaterThan(new Score(20))).isFalse();
    }

    @Test
    void 점수가_다른_점수보다_작으면_참이다() {
        assertThat(new Score(18).isLessThan(new Score(20))).isTrue();
    }

    @Test
    void 점수가_다른_점수와_같으면_isLessThanOrEqualTo가_참이다() {
        assertThat(new Score(16).isLessThanOrEqualTo(new Score(16))).isTrue();
    }

    @Test
    void 점수가_다른_점수보다_크면_isLessThanOrEqualTo가_거짓이다() {
        assertThat(new Score(17).isLessThanOrEqualTo(new Score(16))).isFalse();
    }
}

package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class BustPolicyTest {

    private static final int BUST_THRESHOLD = 21;

    private final BustPolicyImpl bustPolicy = new BustPolicyImpl(BUST_THRESHOLD);

    @Test
    void 점수가_임계치를_초과하면_버스트로_판단한다() {
        // given
        Score score = new Score(BUST_THRESHOLD + 1);
        // when
        boolean bust = bustPolicy.isBust(score);
        // then
        assertThat(bust).isTrue();
    }

    @Test
    void 점수가_임계치_이하이면_버스트로_판단하지_않는다() {
        // given
        Score score = new Score(BUST_THRESHOLD);
        // when
        boolean bust = bustPolicy.isBust(score);
        // then
        assertThat(bust).isTrue();
    }
}

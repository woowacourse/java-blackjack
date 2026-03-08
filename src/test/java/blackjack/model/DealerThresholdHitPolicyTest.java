package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class DealerThresholdHitPolicyTest {

    private static final int THRESHOLD = 16;

    @Test
    void 점수가_임계점을_초과하면_스탠드한다() {
        // given
        DealerThresholdHitPolicy dealerHitPolicy = new DealerThresholdHitPolicy(THRESHOLD);
        Score score = new Score(THRESHOLD + 1);
        // when
        boolean shouldHit = dealerHitPolicy.shouldHit(score);
        // then
        assertThat(shouldHit).isFalse();
    }

    @Test
    void 점수가_임계점_이하면_힛한다() {
        // given
        DealerThresholdHitPolicy dealerHitPolicy = new DealerThresholdHitPolicy(THRESHOLD);
        Score score = new Score(THRESHOLD);
        // when
        boolean shouldHit = dealerHitPolicy.shouldHit(score);
        // then
        assertThat(shouldHit).isTrue();
    }
}

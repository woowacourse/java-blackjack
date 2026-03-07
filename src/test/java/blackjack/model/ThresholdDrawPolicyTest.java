package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ThresholdDrawPolicyTest {

    static final int THRESHOLD = 17;

    @ParameterizedTest
    @ValueSource(ints = {17, 100, 500, Integer.MAX_VALUE})
    void 점수가_임계점_이상이라면_false를_반환한다(int score) {
        // given
        ThresholdDrawPolicy dealerDrawPolicy = new ThresholdDrawPolicy(THRESHOLD);

        // when
        boolean shouldDraw = dealerDrawPolicy.shouldDraw(score);

        // then
        assertThat(shouldDraw).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {16, 0, -100, Integer.MIN_VALUE})
    void 점수가_임계점_미만이라면_true를_반환한다(int score) {
        // given
        ThresholdDrawPolicy dealerDrawPolicy = new ThresholdDrawPolicy(THRESHOLD);

        // when
        boolean shouldDraw = dealerDrawPolicy.shouldDraw(score);

        // then
        assertThat(shouldDraw).isTrue();
    }
}

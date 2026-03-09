package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    static final int ADJUST_VALUE = 10;

    @Nested
    @DisplayName("카드를 더 뽑아야 하는지 판단한다")
    class JudgeShouldDraw {
        @Test
        void 카드를_더_뽑아야_한다면_true를_반환한다() {
            // given
            DealerDrawPolicy alwaysDrawPolicy = (score) -> true;
            Dealer dealer = new Dealer(alwaysDrawPolicy);

            // when
            boolean shouldDraw = dealer.shouldDraw();

            // then
            assertThat(shouldDraw).isTrue();
        }

        @Test
        void 카드를_더_뽑지_말아야_한다면_false를_반환한다() {
            // given
            DealerDrawPolicy neverDrawPolicy = (score) -> false;
            Dealer dealer = new Dealer(neverDrawPolicy);

            // when
            boolean shouldDraw = dealer.shouldDraw();

            // then
            assertThat(shouldDraw).isFalse();
        }
    }
}

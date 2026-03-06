package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    private final Hand emptyHand = new Hand(new AceAdjustPolicy(new BustPolicyImpl()));

    @Nested
    @DisplayName("카드를 더 뽑아야 하는지 판단한다")
    class JudgeShouldDraw {
        @Test
        void 카드를_더_뽑아야_한다면_true를_반환한다() {
            // given
            DealerDrawPolicy alwaysDrawPolicy = (score) -> true;
            Dealer dealer = new Dealer(emptyHand, alwaysDrawPolicy);

            // when
            boolean shouldDraw = dealer.shouldDraw();

            // then
            assertThat(shouldDraw).isTrue();
        }

        @Test
        void 카드를_더_뽑지_말아야_한다면_false를_반환한다() {
            // given
            DealerDrawPolicy neverDrawPolicy = (score) -> false;
            Dealer dealer = new Dealer(emptyHand, neverDrawPolicy);

            // when
            boolean shouldDraw = dealer.shouldDraw();

            // then
            assertThat(shouldDraw).isFalse();
        }
    }
}

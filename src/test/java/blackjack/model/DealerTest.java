package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    private final Hand emptyHand = new Hand(new ScoreCalculator());

    @Nested
    @DisplayName("카드를 더 뽑아야 하는지 판단한다")
    class JudgeShouldHit {
        @Test
        void 카드를_더_뽑아야_한다면_true를_반환한다() {
            // given
            DealerHitPolicy alwaysHitPolicy = (score) -> true;
            Dealer dealer = new Dealer(emptyHand, alwaysHitPolicy);
            // when
            boolean shouldDraw = dealer.shouldHit();
            // then
            assertThat(shouldDraw).isTrue();
        }

        @Test
        void 카드를_더_뽑지_말아야_한다면_false를_반환한다() {
            // given
            DealerHitPolicy neverHitPolicy = (score) -> false;
            Dealer dealer = new Dealer(emptyHand, neverHitPolicy);
            // when
            boolean shouldDraw = dealer.shouldHit();
            // then
            assertThat(shouldDraw).isFalse();
        }
    }
}

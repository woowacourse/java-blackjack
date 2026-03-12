package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    private static final BustPolicy NEVER_BUST_POLICY = score -> false;
    private final ScoreCalculator scoreCalculator = new ScoreCalculator(NEVER_BUST_POLICY);
    private final Hand emptyHand = new Hand();

    @Nested
    @DisplayName("카드를 더 뽑아야 하는지 판단한다")
    class JudgeShouldHit {
        @Test
        void 카드를_더_뽑아야_한다면_true를_반환한다() {
            // given
            DealerHitPolicy alwaysHitPolicy = (score) -> true;
            Dealer dealer = new Dealer("딜러", emptyHand);
            // when
            boolean shouldDraw = dealer.shouldHit(alwaysHitPolicy,
                scoreCalculator.calculateOptimalTotal(dealer.getCards()));
            // then
            assertThat(shouldDraw).isTrue();
        }

        @Test
        void 카드를_더_뽑지_말아야_한다면_false를_반환한다() {
            // given
            DealerHitPolicy neverHitPolicy = (score) -> false;
            Dealer dealer = new Dealer("딜러", emptyHand);
            // when
            boolean shouldDraw = dealer.shouldHit(neverHitPolicy,
                scoreCalculator.calculateOptimalTotal(dealer.getCards()));
            // then
            assertThat(shouldDraw).isFalse();
        }
    }
}

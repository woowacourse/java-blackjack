package blackjack.domain.strategy;

import java.util.function.Supplier;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Score;
import blackjack.domain.strategy.hit.DealerHitStrategy;
import blackjack.domain.strategy.hit.HitStrategy;
import blackjack.domain.strategy.hit.PlayerHitStrategy;

class HitStrategyTest {

    @Test
    @DisplayName("(플레이어) 참이 입력되면 카드를 한장 더 받을 수 있다.")
    public void testIsHitOfPlayerWithY() {
        // given
        boolean input = true;
        // when
        HitStrategy strategy = new PlayerHitStrategy(() -> input);

        // then
        Assertions.assertThat(strategy.isHit()).isTrue();
    }

    @Test
    @DisplayName("(플레이어) n이 입력되면 카드를 더이상 받지 않는다.")
    public void testIsStayOfPlayerWithN() {
        // given
        boolean input = false;
        // when
        HitStrategy strategy = new PlayerHitStrategy(() -> input);
        // then
        Assertions.assertThat(strategy.isHit()).isFalse();
    }

    @Test
    @DisplayName("(딜러) 현재 점수가 17보다 낮으면 카드를 받을 수 있다.")
    public void testIsIHitOfDealerWithScoreLowerThan17() {
        // given
        Supplier<Score> supplier = () -> new Score(16);
        // when
        HitStrategy strategy = new DealerHitStrategy(supplier);
        // then
        Assertions.assertThat(strategy.isHit()).isTrue();
    }

    @Test
    @DisplayName("(딜러) 현재 점수가 17보다 크거나 같으면 카드를 받을 수 없다.")
    public void testIsStayOfDealerWithScoreGreaterOrEqualThan17() {
        // given
        Supplier<Score> supplier = () -> new Score(17);
        // when
        HitStrategy strategy = new DealerHitStrategy(supplier);
        // then
        Assertions.assertThat(strategy.isHit()).isFalse();
    }
}
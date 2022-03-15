package blackjack.domain.strategy;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Score;
import blackjack.domain.strategy.hit.DealerHitStrategy;
import blackjack.domain.strategy.hit.HitStrategy;
import blackjack.domain.strategy.hit.PlayerHitStrategy;

class HitStrategyTest {

    @Test
    @DisplayName("(플레이어) y가 입력되면 카드를 한장 더 받을 수 있다.")
    public void testIsHitOfPlayerWithY() {
        // given
        String input = "y";
        // when
        HitStrategy strategy = new PlayerHitStrategy(() -> input);

        // then
        Assertions.assertThat(strategy.isHit()).isTrue();
    }

    @Test
    @DisplayName("(플레이어) n이 입력되면 카드를 더이상 받지 않는다.")
    public void testIsStayOfPlayerWithN() {
        // given
        String input = "n";
        // when
        HitStrategy strategy = new PlayerHitStrategy(() -> input);
        // then
        Assertions.assertThat(strategy.isHit()).isFalse();
    }

    @Test
    @DisplayName("(플레이어) y또는 n이 아니면 예외를 던진다.")
    public void throwsExceptionWhenInputIsNotWhetherYOrN() {
        // when
        String input = "a";
        // then
        HitStrategy strategy = new PlayerHitStrategy(() -> input);

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(strategy::isHit);
    }

    @Test
    @DisplayName("(딜러) 현재 점수가 17보다 낮으면 카드를 받을 수 있다.")
    public void testIsIHitOfDealerWithScoreLowerThan17() {
        // given
        Score score = new Score(16);
        // when
        HitStrategy strategy = new DealerHitStrategy(score);
        // then
        Assertions.assertThat(strategy.isHit()).isTrue();
    }

    @Test
    @DisplayName("(딜러) 현재 점수가 17보다 크거나 같으면 카드를 받을 수 없다.")
    public void testIsStayOfDealerWithScoreGreaterOrEqualThan17() {
        // given
        Score score = new Score(17);
        // when
        HitStrategy strategy = new DealerHitStrategy(score);
        // then
        Assertions.assertThat(strategy.isHit()).isFalse();
    }
}
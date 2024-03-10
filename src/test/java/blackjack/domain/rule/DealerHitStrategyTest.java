package blackjack.domain.rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerHitStrategyTest {

    private DealerHitStrategy dealerHitStrategy;

    @BeforeEach
    void setUp() {
        dealerHitStrategy = new DealerHitStrategy();
    }

    @DisplayName("딜러는 16 이하이면 카드를 뽑을 수 있다.")
    @Test
    void testCanHit() {
        // when
        boolean actual = dealerHitStrategy.canHit(16);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("딜러는 17점 이상이면 카드를 뽑을 수 없다.")
    @Test
    void testCanNotHit() {
        // when
        boolean actual = dealerHitStrategy.canHit(17);

        // then
        assertThat(actual).isFalse();
    }
}

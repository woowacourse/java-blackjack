package blackjack.domain.rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class DealerHitStrategyTest {

    private DealerHitStrategy dealerHitStrategy;

    @BeforeEach
    void setUp() {
        dealerHitStrategy = new DealerHitStrategy();
    }

    @DisplayName("딜러는 17점 미만이면 카드를 받아야 한다")
    @ParameterizedTest
    @ValueSource(ints = {13, 14, 15, 16})
    void testDealerShouldHit(int score) {
        boolean canHit = dealerHitStrategy.canHit(new Score(score));
        assertThat(canHit).isTrue();
    }

    @DisplayName("딜러는 17점 이상이면 카드를 받을 수 없다")
    @ParameterizedTest
    @ValueSource(ints = {17, 18, 19, 20})
    void testDealerShouldStay(int score) {
        boolean canHit = dealerHitStrategy.canHit(new Score(score));
        assertThat(canHit).isFalse();
    }
}
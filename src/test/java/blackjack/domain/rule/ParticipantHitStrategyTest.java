package blackjack.domain.rule;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ParticipantHitStrategyTest {

    private PlayerHitStrategy playerHitStrategy;

    @BeforeEach
    void setUp() {
        playerHitStrategy = new PlayerHitStrategy();
    }

    @DisplayName("플레이어는 21점 이하면 카드를 받을 수 있다")
    @ParameterizedTest
    @ValueSource(ints = {20, 21})
    void testDealerShouldHit(int score) {
        boolean canHit = playerHitStrategy.canHit(new Score(score));
        assertThat(canHit).isTrue();
    }

    @DisplayName("플레이어는 21점을 초과하면 카드를 받을 수 없다")
    @ParameterizedTest
    @ValueSource(ints = {22, 23})
    void testDealerShouldStay(int score) {
        boolean canHit = playerHitStrategy.canHit(new Score(score));
        assertThat(canHit).isFalse();
    }
}

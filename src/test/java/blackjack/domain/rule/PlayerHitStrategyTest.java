package blackjack.domain.rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerHitStrategyTest {

    private PlayerHitStrategy playerHitStrategy;

    @BeforeEach
    void setUp() {
        playerHitStrategy = new PlayerHitStrategy();
    }

    @DisplayName("플레이어는 21을 넘지 않을 경우 카드를 뽑을 수 있다.")
    @Test
    void testCanHit() {
        // when
        boolean actual = playerHitStrategy.canHit(21);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("플레이어는 21을 넘을 경우 카드를 뽑을 수 없다.")
    @Test
    void testCanNotHit() {
        // when
        boolean actual = playerHitStrategy.canHit(22);

        // then
        assertThat(actual).isFalse();
    }
}

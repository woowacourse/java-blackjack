package blackjack.domain.state;

import blackjack.domain.Hand;
import blackjack.domain.TestSetUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StateFactoryTest {

    @DisplayName("블랙잭, HIT 상태 반환 테스트")
    @Test
    void getState() {
        Hand blackjackHand = TestSetUp.createBlackJackPlayer().getHand();
        assertThat(StateFactory.getInstance(blackjackHand))
                .isInstanceOf(BlackJackState.class);

        Hand hitHand = TestSetUp.createTiePlayer().getHand();
        assertThat(StateFactory.getInstance(hitHand))
                .isInstanceOf(Hit.class);
    }
}
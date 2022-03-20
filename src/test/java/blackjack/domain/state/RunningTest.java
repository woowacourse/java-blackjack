package blackjack.domain.state;

import static blackjack.domain.TestBlackjackUtils.createCardHand;
import static blackjack.domain.TestCardFixture.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RunningTest {

    @Test
    void isFinished() {
        State state = new Ready();

        assertThat(state.isFinished()).isFalse();
    }

    @Test
    void throwExceptionEarningRate() {
        State state = new Ready();
        State dealerState = new Stay(createCardHand(aceCard, sevenCard));

        assertThatThrownBy(() -> state.earningRate(dealerState))
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessageContaining("[ERROR] 수익율을 구하는걸 지원하지 않습니다.");
    }
}

package blackjack.domain.state;

import blackjack.domain.TestSetUp;
import blackjack.domain.state.finished.BlackJackState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackStateTest {
    @Test
    @DisplayName("블랙잭 상태 수익률 검증")
    void checkResult() {
        BlackJackState blackJackState = new BlackJackState();
        assertThat(blackJackState.profitRate(TestSetUp.createDealer(), 21)).isEqualTo(1.5);
        assertThat(blackJackState.profitRate(TestSetUp.createBlackJackDealer(), 21)).isEqualTo(0);
    }
}
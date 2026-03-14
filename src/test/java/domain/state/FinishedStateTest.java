package domain.state;

import domain.member.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinishedStateTest {

    @DisplayName("Bust 상태는 수익률 -1.0을 반환하고 종료 상태이다")
    @Test
    void bust_EarningRate_IsMinusOne() {
        State state = new Bust(new Hand());
        assertThat(state.isFinished()).isTrue();
        assertThat(state.earningRate()).isEqualTo(-1.0);
    }

    @DisplayName("Blackjack 상태는 수익률 1.5을 반환하고 종료 상태이다")
    @Test
    void blackjack_EarningRate_IsOnePointFive() {
        State state = new Blackjack(new Hand());
        assertThat(state.isFinished()).isTrue();
        assertThat(state.earningRate()).isEqualTo(1.5);
    }
}

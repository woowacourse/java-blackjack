package blackjack.domain.user.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.HandFixtures;
import blackjack.domain.money.Money;

class RunningTest {

    private static final Running CONCRETE_RUNNING = new Hit(HandFixtures.STAY_HAND_15);

    @Test
    @DisplayName("Running은 종료상태가 아니다.")
    public void testIfStateFinished() {
        // given
        State state = CONCRETE_RUNNING;
        // when
        boolean isFinished = state.isFinished();
        // then
        assertThat(isFinished).isFalse();
    }

    @Test
    @DisplayName("수익률을 계산할 수 없다.")
    public void throwsExceptionWhenCalculatingProfit() {
        // given & when
        Money money = new Money(1000);
        State playerState = CONCRETE_RUNNING;
        State dealerState = CONCRETE_RUNNING;

        // then
        assertThatExceptionOfType(IllegalStateException.class)
            .isThrownBy(() -> playerState.calculateProfit(money, dealerState));
    }
}
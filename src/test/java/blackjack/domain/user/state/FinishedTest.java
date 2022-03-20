package blackjack.domain.user.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.CardFixtures;
import blackjack.domain.HandFixtures;
import blackjack.domain.money.BettingMoney;
import blackjack.domain.money.Money;

class FinishedTest {

    private static final Finished CONCRETE_FINISHED = new Stay(HandFixtures.STAY_HAND_17); 
    
    @Test
    @DisplayName("히트시 예외를 던진다.")
    public void throwsExceptionWhenTryingToHit() {
        // given & when
        State state = CONCRETE_FINISHED;
        // then
        assertThatExceptionOfType(IllegalStateException.class)
            .isThrownBy(() -> state.hit(CardFixtures.FIVE));
    }
    
    @Test
    @DisplayName("스테이시 예외를 던진다.")
    public void throwsExceptionWhenTryingToStay() {
        // given & when
        State state = CONCRETE_FINISHED;
        // then
        assertThatExceptionOfType(IllegalStateException.class)
            .isThrownBy(state::stay);
    }

    @Test
    @DisplayName("끝난 상태인지 확인한다.")
    public void testIfStateFinished() {
        // given
        State state = CONCRETE_FINISHED;
        // when
        boolean isFinished = state.isFinished();
        // then
        assertThat(isFinished).isTrue();
    }

    @Test
    @DisplayName("수익을 계산할 수 있다.")
    public void testProfitCalculation() {
        // given
        Money money = new BettingMoney(1000);
        State playerState = CONCRETE_FINISHED;
        State dealerState = CONCRETE_FINISHED;
        // when
        Money profit = playerState.calculateProfit(money, dealerState);
        // then
        assertThat(profit.getAmount()).isEqualTo(0);
    }
}
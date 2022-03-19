package blackjack.domain.user.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.CardFixtures;
import blackjack.domain.HandFixtures;
import blackjack.domain.money.Money;

class StayTest {

    @Test
    @DisplayName("히트시 예외를 던진다.")
    public void throwsExceptionOnHit() {
        // given & when
        State state = new Stay(HandFixtures.STAY_HAND_15);
        // then
        assertThatExceptionOfType(IllegalStateException.class)
            .isThrownBy(() -> state.hit(CardFixtures.ACE));
    }

    @Test
    @DisplayName("스테이시 예외를 던진다.")
    public void throwsExceptionOnStay() {
        State state = new Stay(HandFixtures.STAY_HAND_15);
        // then
        assertThatExceptionOfType(IllegalStateException.class)
            .isThrownBy(state::stay);
    }

    @Test
    @DisplayName("블랙잭을 상대로 패배한다.")
    public void loseAgainstBlackjack() {
        // given
        State playerState = new Stay(HandFixtures.STAY_HAND_15);
        State dealerState = new BlackJack(HandFixtures.BLACKJACK_HAND);
        // when
        Money money = playerState.calculateProfit(new Money(1000), dealerState);
        // then
        assertThat(money.getAmount()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("버스트를 상대로 승리한다.")
    public void winAgainstBust() {
        // given
        State playerState = new Stay(HandFixtures.STAY_HAND_15);
        State dealerState = new Bust(HandFixtures.BUST_HAND);
        // when
        Money profit = playerState.calculateProfit(new Money(1000), dealerState);
        // then
        assertThat(profit.getAmount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("높은 점수로 승리한다")
    public void winAgainstStayWithHigherScore() {
        // given
        State playerState = new Stay(HandFixtures.STAY_HAND_17);
        State dealerState = new Stay(HandFixtures.STAY_HAND_15);
        // when
        Money profit = playerState.calculateProfit(new Money(1000), dealerState);
        // then
        assertThat(profit.getAmount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("낮은 점수로 패배한다.")
    public void loseAgainstStayWithLowerScore() {
        // given
        State playerState = new Stay(HandFixtures.STAY_HAND_15);
        State dealerState = new Stay(HandFixtures.STAY_HAND_17);
        // when
        Money profit = playerState.calculateProfit(new Money(1000), dealerState);
        // then
        assertThat(profit.getAmount()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("같은 점수로 비긴다.")
    public void tieAgainstStayWithSameScore() {
        // given
        State playerState = new Stay(HandFixtures.STAY_HAND_17);
        State dealerState = new Stay(HandFixtures.STAY_HAND_17);
        // when
        Money profit = playerState.calculateProfit(new Money(1000), dealerState);
        // then
        assertThat(profit.getAmount()).isEqualTo(0);
    }
}
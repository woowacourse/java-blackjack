package blackjack.domain.user.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.CardFixtures;
import blackjack.domain.HandFixtures;
import blackjack.domain.money.Money;

class BustTest {

    @Test
    @DisplayName("버스트는 블랙잭이 아니다.")
    public void testIsBlackJackFalse() {
        // given
        State state = new Bust(HandFixtures.BUST_HAND);
        // when
        boolean isBlackJack = state.isBlackjack();
        // then
        assertThat(isBlackJack).isFalse();
    }

    @Test
    @DisplayName("버스트를 상대로 패배한다.")
    public void loseAgainstBust() {
        // given
        State playerState = new Bust(HandFixtures.BUST_HAND);
        State dealerState = new Bust(HandFixtures.BUST_HAND);
        // when
        Money money = playerState.calculateProfit(new Money(1000), dealerState);
        // then
        assertThat(money.getAmount()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("히트시 예외를 던진다.")
    public void throwsExceptionWhenTryingHit() {
        // given & when
        State state = new Bust(HandFixtures.BUST_HAND);
        // then
        assertThatExceptionOfType(IllegalStateException.class)
            .isThrownBy(() -> state.hit(CardFixtures.FIVE));
    }

    @Test
    @DisplayName("스테이시 예외를 던진다.")
    public void throwsExceptionWhenTryingStay() {
        // given & when
        State state = new Bust(HandFixtures.BUST_HAND);
        // then
        assertThatExceptionOfType(IllegalStateException.class)
            .isThrownBy(state::stay);
    }
}
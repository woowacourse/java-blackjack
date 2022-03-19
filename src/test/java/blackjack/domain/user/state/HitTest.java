package blackjack.domain.user.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.CardFixtures;
import blackjack.domain.HandFixtures;
import blackjack.domain.money.Money;

class HitTest {

    @Test
    @DisplayName("카드를 받고 21 이하이면 히트가 된다")
    public void hitAndGetHit() {
        // given
        State state = new Hit(HandFixtures.STAY_HAND_15);
        // when
        State hit = state.hit(CardFixtures.FIVE);
        // then
        assertThat(hit).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드를 받고 21 초과이면 히트가 된다")
    public void hitAndGetBust() {
        // given
        State state = new Hit(HandFixtures.STAY_HAND_15);
        // when
        State hit = state.hit(CardFixtures.TEN);
        // then
        assertThat(hit).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("수익 계산시 예외를 던진다.")
    public void throwsExceptionOnCalculateProfit() {
        // given & when
        State state = new Hit(HandFixtures.STAY_HAND_15);
        // then
        assertThatExceptionOfType(IllegalStateException.class)
            .isThrownBy(() -> state.calculateProfit(new Money(1000), new Stay(HandFixtures.STAY_HAND_15)));
    }

}
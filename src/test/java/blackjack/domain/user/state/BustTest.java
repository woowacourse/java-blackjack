package blackjack.domain.user.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.HandFixtures;
import blackjack.domain.money.Money;

class BustTest {

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
}
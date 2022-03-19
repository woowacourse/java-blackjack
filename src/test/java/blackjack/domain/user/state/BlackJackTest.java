package blackjack.domain.user.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.HandFixtures;
import blackjack.domain.money.Money;

class BlackJackTest {

    @Test
    @DisplayName("블랙잭을 상대로 비긴다.")
    public void tieAgainstDealerBlackjack() {
        // given
        State playerState = new BlackJack(HandFixtures.BLACKJACK_HAND);
        State dealerState = new BlackJack(HandFixtures.BLACKJACK_HAND);

        // when
        Money profit = playerState.calculateProfit(new Money(1000), dealerState);
        // then
        assertThat(profit.getAmount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("스테이를 상대로 승리한다.")
    public void winAgainstDealerStay() {
        // given
        State playerState = new BlackJack(HandFixtures.BLACKJACK_HAND);
        State dealerState = new Stay(HandFixtures.STAY_HAND_17);

        // when
        Money profit = playerState.calculateProfit(new Money(1000), dealerState);
        // then
        assertThat(profit.getAmount()).isEqualTo(1500);
    }

    @Test
    @DisplayName("버스트를 상대로 승리한다.")
    public void winAgainstDealerBust() {
        // given
        State playerState = new BlackJack(HandFixtures.BLACKJACK_HAND);
        State dealerState = new Stay(HandFixtures.BUST_HAND);

        // when
        Money profit = playerState.calculateProfit(new Money(1000), dealerState);
        // then
        assertThat(profit.getAmount()).isEqualTo(1500);
    }
}
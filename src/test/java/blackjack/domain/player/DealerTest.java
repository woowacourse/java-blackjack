package blackjack.domain.player;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_JACK;
import static blackjack.Fixture.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.state.BlackJack;
import blackjack.domain.state.Bust;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 카드 추가 테스트")
    public void dealerAddCardTest() {
        Dealer dealer = Dealer.of();

        dealer.addCard(SPADE_ACE);
        dealer.addCard(SPADE_JACK);

        assertThat(dealer.getPoint()).isEqualTo(21);
    }

    @Test
    @DisplayName("딜러 Hit일 경우 테스트")
    public void isHit() {
        Dealer dealer = Dealer.of();

        dealer.addCard(SPADE_ACE);
        dealer.addCard(SPADE_JACK);

        assertThat(dealer.isHit()).isEqualTo(false);
    }

    @Test
    @DisplayName("딜러 Hit가 아닐 경우 테스트")
    public void isNonHit() {
        Dealer dealer = Dealer.of();

        dealer.addCard(SPADE_TWO);
        dealer.addCard(SPADE_JACK);

        assertThat(dealer.isHit()).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러 BlackJack 테스트")
    public void isBlackJack() {
        Dealer dealer = Dealer.of();

        dealer.addCard(SPADE_ACE);
        dealer.addCard(SPADE_JACK);

        assertThat(dealer.getState()).isInstanceOf(BlackJack.class);
    }

    @Test
    @DisplayName("딜러 Bust 테스트")
    public void isBustJack() {
        Dealer dealer = Dealer.of();

        dealer.addCard(SPADE_TWO);
        dealer.addCard(SPADE_JACK);
        dealer.addCard(SPADE_JACK);

        assertThat(dealer.getState()).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("딜러 BlackJack일 때 카드 뽑을 경우 예외 발생 테스트")
    public void isStayJack() {
        Dealer dealer = Dealer.of();

        dealer.addCard(SPADE_ACE);
        dealer.addCard(SPADE_JACK);

        assertThatThrownBy(() -> dealer.addCard(SPADE_JACK))
            .isInstanceOf(IllegalStateException.class);
    }
}

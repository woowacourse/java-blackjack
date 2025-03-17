package state.finished;

import static card.Rank.ACE;
import static card.Rank.JACK;
import static card.Suit.CLOVER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import card.CardHand;
import fixture.CardFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import state.State;
import state.running.PlayerHit;

class BlackJackTest {
    @Test
    @DisplayName("BlackJack 상태인 경우 isFinished는 true를 반환한다.")
    void testIsFinished() {
        // given
        CardHand blackjackHand = new CardHand(CardFixture.of(ACE, CLOVER), CardFixture.of(JACK, CLOVER));
        State blackjackState = PlayerHit.initialState(blackjackHand);
        // when
        boolean actual = blackjackState.isFinished();
        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("Blackjack 상태인 경우 카드를 받을 수 없다.")
    void testReceiveCard() {
        // given
        CardHand blackjackHand = new CardHand(CardFixture.of(ACE, CLOVER), CardFixture.of(JACK, CLOVER));
        State blackjackState = PlayerHit.initialState(blackjackHand);
        // when
        // then
        assertThatThrownBy(() -> blackjackState.receiveCard(null))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Blackjack 상태인 경우 Stay 할 수 없다.")
    void testStay() {
        // given
        CardHand blackjackHand = new CardHand(CardFixture.of(ACE, CLOVER), CardFixture.of(JACK, CLOVER));
        State blackjackState = PlayerHit.initialState(blackjackHand);
        // when
        // then
        assertThatThrownBy(blackjackState::stay)
                .isInstanceOf(IllegalStateException.class);
    }
}

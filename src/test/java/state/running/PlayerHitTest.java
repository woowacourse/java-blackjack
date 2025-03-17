package state.running;

import static card.Rank.ACE;
import static card.Rank.FIVE;
import static card.Rank.JACK;
import static card.Rank.TEN;
import static card.Suit.CLOVER;
import static card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import card.CardHand;
import fixture.CardFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import state.State;
import state.finished.BlackJack;
import state.finished.Bust;
import state.finished.Stay;

class PlayerHitTest {
    @Test
    @DisplayName("처음 뽑은 카드가 BlackJack이면 BlackJack을 반환한다.")
    void testBlackJackState() {
        // given
        CardHand blackjackHand = new CardHand(CardFixture.of(ACE, CLOVER), CardFixture.of(JACK, CLOVER));
        // when
        State state = PlayerHit.initialState(blackjackHand);
        // then
        assertThat(state).isInstanceOf(BlackJack.class);
    }

    @Test
    @DisplayName("처음 뽑은 카드가 블랙잭이 아니면 PlayerHit을 반환한다.")
    void testPlayerHitState() {
        // given
        CardHand hitHand = new CardHand(CardFixture.of(ACE, CLOVER), CardFixture.of(FIVE, CLOVER));
        // when
        State state = PlayerHit.initialState(hitHand);
        // then
        assertThat(state).isInstanceOf(PlayerHit.class);
    }

    @Test
    @DisplayName("추가로 카드를 뽑아서 21을 초과하면 Bust를 반환한다.")
    void testBustState() {
        // given
        CardHand hitHand = new CardHand(CardFixture.of(TEN, CLOVER), CardFixture.of(FIVE, HEART));
        State hitState = PlayerHit.initialState(hitHand);
        // when
        State state = hitState.receiveCard(CardFixture.of(TEN, HEART));
        // then
        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("플레이어는 원하는 경우 Stay를 할 수 있다..")
    void testChooseStayState() {
        // given
        CardHand hitHand = new CardHand(CardFixture.of(TEN, CLOVER), CardFixture.of(FIVE, HEART));
        State hitState = PlayerHit.initialState(hitHand);
        // when
        State stay = hitState.stay();
        // then
        assertThat(stay).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("Hit 상태인 경우 isFinished는 false를 반환한다.")
    void testIsFinished() {
        // given
        CardHand hitHand = new CardHand(CardFixture.of(TEN, CLOVER), CardFixture.of(FIVE, HEART));
        // when
        State hitState = DealerHit.initialState(hitHand);
        // then
        assertThat(hitState.isFinished()).isFalse();
    }
}

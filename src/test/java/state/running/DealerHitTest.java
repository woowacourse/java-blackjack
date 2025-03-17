package state.running;

import static card.Rank.ACE;
import static card.Rank.FIVE;
import static card.Rank.JACK;
import static card.Rank.SIX;
import static card.Rank.TEN;
import static card.Rank.TWO;
import static card.Suit.CLOVER;
import static card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import card.CardHand;
import fixture.CardFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import state.State;
import state.finished.BlackJack;
import state.finished.Bust;
import state.finished.Stay;

class DealerHitTest {
    @Test
    @DisplayName("처음 뽑은 카드가 BlackJack이면 BlackJack을 반환한다.")
    void testBlackJackState() {
        // given
        CardHand blackjackHand = new CardHand(CardFixture.of(ACE, CLOVER), CardFixture.of(JACK, CLOVER));
        // when
        State state = DealerHit.initialState(blackjackHand);
        // then
        assertThat(state).isInstanceOf(BlackJack.class);
    }

    @Test
    @DisplayName("처음 뽑은 카드가 17 이상이면 Stay를 반환한다.")
    void testStayState() {
        // given
        CardHand hitHand = new CardHand(CardFixture.of(ACE, CLOVER), CardFixture.of(SIX, CLOVER));
        // when
        State state = DealerHit.initialState(hitHand);
        // then
        assertThat(state).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("처음 뽑은 카드가 16 이하이면 DealerHit을 반환한다.")
    void testDealerHitState() {
        // given
        CardHand hitHand = new CardHand(CardFixture.of(ACE, CLOVER), CardFixture.of(FIVE, CLOVER));
        // when
        State state = DealerHit.initialState(hitHand);
        // then
        assertThat(state).isInstanceOf(DealerHit.class);
    }

    @Test
    @DisplayName("추가로 카드를 뽑아서 21을 초과하면 Bust를 반환한다.")
    void testBustState() {
        // given
        CardHand hitHand = new CardHand(CardFixture.of(TEN, CLOVER), CardFixture.of(FIVE, HEART));
        State hitState = DealerHit.initialState(hitHand);
        // when
        State state = hitState.receiveCard(CardFixture.of(TEN, HEART));
        // then
        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("추가로 카드를 뽑아서 17이상이고 21이하이면 을 초과하면 Stay를 반환한다.")
    void testHitStayState() {
        // given
        CardHand hitHand = new CardHand(CardFixture.of(TEN, CLOVER), CardFixture.of(FIVE, HEART));
        State hitState = DealerHit.initialState(hitHand);
        // when
        State state = hitState.receiveCard(CardFixture.of(TWO, HEART));
        // then
        assertThat(state).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("딜러는 임의로 Stay를 할 수 없다.")
    void testChooseStayState() {
        // given
        CardHand hitHand = new CardHand(CardFixture.of(TEN, CLOVER), CardFixture.of(FIVE, HEART));
        State hitState = DealerHit.initialState(hitHand);
        // when
        // then
        assertThatThrownBy(hitState::stay)
                .isInstanceOf(IllegalStateException.class);
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

package state.finished;

import static card.Rank.FIVE;
import static card.Rank.TEN;
import static card.Suit.CLOVER;
import static card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import card.CardHand;
import fixture.CardFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import state.State;
import state.running.PlayerHit;

class BustTest {
    @Test
    @DisplayName("Bust 상태인 경우 isFinished는 true를 반환한다.")
    void testIsFinished() {
        // given
        CardHand hitHand = new CardHand(CardFixture.of(TEN, CLOVER), CardFixture.of(FIVE, HEART));
        State hitState = PlayerHit.initialState(hitHand);
        State bustState = hitState.receiveCard(CardFixture.of(TEN, HEART));
        // when
        boolean actual = bustState.isFinished();
        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("Bust 상태인 경우 카드를 받을 수 없다.")
    void testReceiveCard() {
        // given
        CardHand hitHand = new CardHand(CardFixture.of(TEN, CLOVER), CardFixture.of(FIVE, HEART));
        State hitState = PlayerHit.initialState(hitHand);
        State bustState = hitState.receiveCard(CardFixture.of(TEN, HEART));
        // when
        // then
        assertThatThrownBy(() -> bustState.receiveCard(null))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Bust 상태인 경우 Stay 할 수 없다.")
    void testStay() {
        // given
        CardHand hitHand = new CardHand(CardFixture.of(TEN, CLOVER), CardFixture.of(FIVE, HEART));
        State hitState = PlayerHit.initialState(hitHand);
        State bustState = hitState.receiveCard(CardFixture.of(TEN, HEART));
        // when
        // then
        assertThatThrownBy(bustState::stay)
                .isInstanceOf(IllegalStateException.class);
    }
}

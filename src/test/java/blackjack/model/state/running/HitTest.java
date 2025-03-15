package blackjack.model.state.running;

import static blackjack.model.card.CardFixtures.SPADE_FIVE_CARD;
import static blackjack.model.card.CardFixtures.SPADE_SIX_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TEN_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TWO_CARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.model.state.Hand;
import blackjack.model.state.State;
import blackjack.model.state.finished.Bust;
import blackjack.model.state.finished.Stand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("히트 테스트")
class HitTest {

    @DisplayName("히트 상태는 카드가 2장 이상이며, 합이 21 미만, 종료되지 않은 상태이다.")
    @Test
    void hitTest() {
        // given
        State hit = create16HitState();

        // when
        Hand hand = hit.getHand();
        int total = hand.getTotal();
        int size = hand.size();

        // when, then
        assertThat(total)
                .isLessThan(21);
        assertThat(size)
                .isGreaterThanOrEqualTo(2);
        assertThat(hit.isFinished())
                .isFalse();
    }

    @DisplayName("히트할 수 있는 상태인 경우 카드를 받을 수 있다.")
    @Test
    void shouldHitTest() {
        // given
        State hit = create16HitState();

        // when, then
        assertThatCode(() -> hit.receiveCard(SPADE_TWO_CARD))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드를 받다가 버스트가 될 수 있다.")
    @Test
    void bustTest() {
        // given
        State hit = create16HitState();

        // when
        State bust = hit.receiveCard(SPADE_TEN_CARD);

        // then
        assertThat(bust)
                .isInstanceOf(Bust.class);
        assertThatCode(() -> bust.receiveCard(SPADE_TEN_CARD))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("버스트되어 카드를 더 받을 수 없습니다.");
    }

    @DisplayName("21이 되어 멈출 수 있다.")
    @Test
    void standTest() {
        // given
        State hit = create16HitState();

        // when
        State stand = hit.receiveCard(SPADE_FIVE_CARD);

        // then
        assertThat(stand.isFinished())
                .isTrue();
        assertThat(stand)
                .isInstanceOf(Stand.class);
        assertThatCode(() -> stand.receiveCard(SPADE_TEN_CARD))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 스탠드하여 카드를 받을 수 없습니다.");
    }

    private State create16HitState() {
        State firstTurn = new InitialDeal();
        return firstTurn.receiveCard(SPADE_TEN_CARD)
                .receiveCard(SPADE_SIX_CARD);
    }
}

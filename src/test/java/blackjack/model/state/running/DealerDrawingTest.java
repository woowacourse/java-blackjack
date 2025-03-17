package blackjack.model.state.running;

import static blackjack.model.card.CardFixtures.SPADE_ACE_CARD;
import static blackjack.model.card.CardFixtures.SPADE_FIVE_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TEN_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TWO_CARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.model.state.State;
import blackjack.model.state.finished.Blackjack;
import blackjack.model.state.finished.Bust;
import blackjack.model.state.finished.Stand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("딜러 상태 테스트")
class DealerDrawingTest {

    @DisplayName("딜러 상태는 합이 16 이하이며, 종료되지 않은 상태이다.")
    @Test
    void dealerDrawingTest() {
        // given
        State dealerState = new DealerDrawing();

        // when
        int total = dealerState.getTotal();

        // then
        assertThat(total)
                .isLessThanOrEqualTo(16);
        assertThat(dealerState.isFinished())
                .isFalse();
    }

    @DisplayName("딜러 상태를 생성할 수 있다.")
    @Test
    void createDealerStateTest() {
        // given
        State dealerState = new DealerDrawing();

        // when, then
        assertThat(dealerState)
                .isInstanceOf(DealerDrawing.class);
    }

    @DisplayName("딜러 상태는 카드를 받을 수 있다.")
    @Test
    void receiveCardTest() {
        // given
        State dealerState = new DealerDrawing();

        // when, then
        assertThatCode(() -> dealerState.receiveCard(SPADE_TWO_CARD))
                .doesNotThrowAnyException();
    }

    @DisplayName("딜러 상태는 블랙잭일 수 없다.")
    @Test
    void notBlackjackTest() {
        // given
        State dealerState = new DealerDrawing();

        // when
        State blackjackDealerState = dealerState.receiveCard(SPADE_TWO_CARD)
                .receiveCard(SPADE_TWO_CARD);

        // then
        assertThat(blackjackDealerState)
                .isInstanceOf(DealerDrawing.class);
        assertThat(blackjackDealerState.isFinished())
                .isEqualTo(false);
    }

    @DisplayName("딜러 상태는 카드 두 장을 받기 전까진 딜러 상태이다.")
    @Test
    void dealerStateTest() {
        // given
        State dealerState = new DealerDrawing();

        // when
        State state1 = dealerState.receiveCard(SPADE_TWO_CARD);
        State state2 = state1.receiveCard(SPADE_TWO_CARD);

        // then
        assertThat(state1)
                .isInstanceOf(DealerDrawing.class);
        assertThat(state2)
                .isInstanceOf(DealerDrawing.class);
    }

    @DisplayName("딜러 상태는 카드 두 장을 받았는데, 21인 경우 블랙잭이다.")
    @Test
    void blackjackTest() {
        // given
        State dealerState = new DealerDrawing();

        // when
        State blackjackDealerState = dealerState.receiveCard(SPADE_TEN_CARD)
                .receiveCard(SPADE_ACE_CARD);

        // then
        assertThat(blackjackDealerState)
                .isInstanceOf(Blackjack.class);
    }

    @DisplayName("딜러 상태는 카드 두 장을 받았는데, 16 이하인 경우 히트할 수 있다.")
    @Test
    void dealerStateHitTest() {
        // given
        State dealerState = new DealerDrawing();

        // when
        State state1 = dealerState.receiveCard(SPADE_FIVE_CARD);
        State state2 = state1.receiveCard(SPADE_TEN_CARD);

        // then
        assertThat(state1)
                .isInstanceOf(DealerDrawing.class);
        assertThat(state2)
                .isInstanceOf(DealerDrawing.class);
    }

    @DisplayName("딜러 상태는 카드 두 장을 받았는데, 16보다 큰 경우 스탠드할 수 없다.")
    @Test
    void dealerStateStandTest() {
        // given
        State dealerState = new DealerDrawing();

        // when
        State state1 = dealerState.receiveCard(SPADE_TEN_CARD);
        State state2 = state1.receiveCard(SPADE_TEN_CARD);

        // then
        assertThat(state1)
                .isInstanceOf(DealerDrawing.class);
        assertThat(state2)
                .isInstanceOf(Stand.class);
    }

    @DisplayName("딜러 상태는 처음부터 스탠드할 수 없다.")
    @Test
    void shouldNotStand() {
        // given
        State dealerState = new DealerDrawing();

        // when, then
        assertThatCode(dealerState::stand)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("딜러는 처음부터 스탠드할 수 없습니다.");
    }

    @DisplayName("딜러 상태는 버스트되어 카드를 더 받을 수 없다.")
    @Test
    void shouldNotReceiveCard() {
        // given
        State dealerState = new DealerDrawing();

        // when
        State newDealerState = dealerState.receiveCard(SPADE_FIVE_CARD)
                .receiveCard(SPADE_TEN_CARD)
                .receiveCard(SPADE_TEN_CARD);

        // then
        assertThat(newDealerState)
                .isInstanceOf(Bust.class);
        assertThatCode(() -> newDealerState.receiveCard(SPADE_TEN_CARD))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("버스트되어 카드를 더 받을 수 없습니다.");
    }
}

package blackjack.domain.participant.state;

import static blackjack.Fixture.HEART_FIVE;
import static blackjack.Fixture.HEART_SEVEN;
import static blackjack.Fixture.HEART_SIX;
import static blackjack.Fixture.SPADE_FIVE;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_QUEEN;
import static blackjack.Fixture.SPADE_TEN;
import static blackjack.Fixture.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;

class HitStateTest {

    @DisplayName("Hit 상태가 되기 위해서는 최소 2장의 카드가 준비되어야 한다.")
    @Test
    void cardSizeExceptionTest() {
        assertThatThrownBy(() -> HitState.of(SPADE_TEN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("카드는 2장 이상이어야 합니다.");
    }

    @DisplayName("Hit 상태가 되기 위해서는 준비된 카드의 합이 21 미만이어야 한다.")
    @Test
    void cardScoreExceptionTest() {
        assertThatThrownBy(() -> HitState.of(SPADE_KING, SPADE_QUEEN, SPADE_TWO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("합계가 21 이상이므로 Hit 상태가 될 수 없습니다.");
    }

    @DisplayName("카드를 뽑았을 때, 보유한 카드의 합이 21 미만이면 Hit 상태를 유지해야 한다.")
    @Test
    void isHitTest() {
        final State state = HitState.of(SPADE_TEN, SPADE_FIVE)
                .drawCard(HEART_FIVE);
        assertThat(state).isInstanceOf(HitState.class);
    }

    @DisplayName("카드를 뽑았을 때, 보유한 카드의 합이 21이면 Stand 상태로 변해야 한다.")
    @Test
    void isStandStateTest() {
        final State state = InitialState.initiallyDrawCards(SPADE_TEN, SPADE_FIVE)
                .drawCard(HEART_SIX);
        assertThat(state).isInstanceOf(StandState.class);
    }

    @DisplayName("카드를 뽑았을 때, 보유한 카드의 합이 21 초과면 Bust 상태로 변해야 한다.")
    @Test
    void isBustTest() {
        final State state = InitialState.initiallyDrawCards(SPADE_TEN, SPADE_FIVE)
                .drawCard(HEART_SEVEN);
        assertThat(state).isInstanceOf(BustState.class);
    }

    @DisplayName("카드를 뽑지 않는다면, Hit 상태에서 Stand 상태로 변경할 수 있어야 한다.")
    @Test
    void judgeMatchStatusExceptionTest() {
        final State state = HitState.of(SPADE_TEN, SPADE_FIVE)
                .stay();
        assertThat(state).isInstanceOf(StandState.class);
    }

    @DisplayName("턴이 종료되었는지 확인할 수 있어야 한다.")
    @Test
    void isFinishedTest() {
        final State state = HitState.of(SPADE_TEN, SPADE_FIVE);
        assertThat(state.isFinished()).isFalse();
    }

    @DisplayName("카드의 합계를 확인할 수 있어야 한다.")
    @Test
    void getScoreTest() {
        final State state = HitState.of(SPADE_TEN, SPADE_FIVE);
        assertThat(state.getScore()).isEqualTo(15);
    }

    @DisplayName("보유한 카드를 확인할 수 있어야 한다.")
    @Test
    void getCardsTest() {
        final State state = HitState.of(SPADE_TEN, SPADE_FIVE);
        final List<Card> actualCards = state.getCards();
        assertThat(actualCards).isEqualTo(List.of(SPADE_TEN, SPADE_FIVE));
    }

}

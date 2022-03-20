package blackjack.domain.participant.state;

import static blackjack.Fixture.SPADE_FIVE;
import static blackjack.Fixture.SPADE_TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;

class HitStateTest {

    private static final HitState HIT_STATE = HitState.of(SPADE_TEN, SPADE_FIVE);

    @DisplayName("Hit 상태가 되기 위해서는 최소 2장의 카드가 준비되어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.HitStateTestProvider#provideForCardSizeExceptionTest")
    void cardSizeExceptionTest(final List<Card> cards) {
        assertThatThrownBy(() -> HitState.from(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("카드는 2장 이상이어야 합니다.");
    }

    @DisplayName("Hit 상태가 되기 위해서는 준비된 카드의 합이 21 미만이어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.HitStateTestProvider#provideForCardScoreExceptionTest")
    void cardScoreExceptionTest(final List<Card> cards) {
        assertThatThrownBy(() -> HitState.from(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("합계가 21 이상이므로 Hit 상태가 될 수 없습니다.");
    }

    @DisplayName("카드를 뽑았을 때, 보유한 카드의 합이 21 미만이면 Hit 상태를 유지해야 한다.")
    @ParameterizedTest(name = "[{index}] 처음 카드 : {0}, 뽑은 카드 : {1}")
    @MethodSource("blackjack.domain.participant.state.provider.HitStateTestProvider#provideForKeepHitStateWhenDrawCardTest")
    void keepHitStateWhenDrawCardTest(final List<Card> cards, final Card drewCard) {
        final State state = HitState.from(cards)
                .drawCard(drewCard);
        assertThat(state).isInstanceOf(HitState.class);
    }

    @DisplayName("카드를 뽑았을 때, 보유한 카드의 합이 21이면 Stand 상태로 변해야 한다.")
    @ParameterizedTest(name = "[{index}] 처음 카드 : {0}, 뽑은 카드 : {1}")
    @MethodSource("blackjack.domain.participant.state.provider.HitStateTestProvider#provideForChangeToStandStateWhenDrawCardTest")
    void changeToStandStateWhenDrawCardTest(final List<Card> cards, final Card drewCard) {
        final State state = HitState.from(cards)
                .drawCard(drewCard);
        assertThat(state).isInstanceOf(StandState.class);
    }

    @DisplayName("카드를 뽑았을 때, 보유한 카드의 합이 21 초과면 Bust 상태로 변해야 한다.")
    @ParameterizedTest(name = "[{index}] 처음 카드 : {0}, 뽑은 카드 : {1}")
    @MethodSource("blackjack.domain.participant.state.provider.HitStateTestProvider#provideForChangeToBustStateWhenDrawCardTest")
    void changeToBustStateWhenDrawCardTest(final List<Card> cards, final Card drewCard) {
        final State state = HitState.from(cards)
                .drawCard(drewCard);
        assertThat(state).isInstanceOf(BustState.class);
    }

    @DisplayName("카드를 뽑지 않는다면, Hit 상태에서 Stand 상태로 변경할 수 있어야 한다.")
    @Test
    void judgeMatchStatusExceptionTest() {
        final State state = HIT_STATE.stay();
        assertThat(state).isInstanceOf(StandState.class);
    }

    @DisplayName("턴이 종료되었는지 확인할 수 있어야 한다.")
    @Test
    void isFinishedTest() {
        assertThat(HIT_STATE.isFinished()).isFalse();
    }

    @DisplayName("카드의 합계를 확인할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 합계 : {1}, 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.HitStateTestProvider#provideForGetScoreTest")
    void getScoreTest(final List<Card> cards, final int expectedScore) {
        final State state = HitState.from(cards);
        final int actualScore = state.getScore();
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @DisplayName("보유한 카드를 확인할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.HitStateTestProvider#provideForGetCardsTest")
    void getCardsTest(final List<Card> expectedCards) {
        final State state = HitState.from(expectedCards);
        final List<Card> actualCards = state.getCards();
        assertThat(actualCards).isEqualTo(expectedCards);
    }

}

package blackjack.domain.participant.state;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_QUEEN;
import static blackjack.Fixture.SPADE_TEN;
import static blackjack.Fixture.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.result.MatchStatus;

class StandStateTest {

    private static final StandState STAND_STATE = StandState.of(SPADE_KING, SPADE_TEN);

    @DisplayName("Stand 상태가 되기 위해서는 최소 2장의 카드가 준비되어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.StandStateTestProvider#provideForCardSizeExceptionTest")
    void cardSizeExceptionTest(final List<Card> cards) {
        assertThatThrownBy(() -> StandState.from(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("카드는 2장 이상이어야 합니다.");
    }

    @DisplayName("Stand 상태가 되기 위해서는 준비된 카드의 합이 21 이하이어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.StandStateTestProvider#provideForCardScoreExceptionTest")
    void cardScoreExceptionTest(final List<Card> cards) {
        assertThatThrownBy(() -> StandState.from(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("합계가 21 이하여야 Stand 상태가 될 수 있습니다.");
    }

    @DisplayName("Stand 상태는 Blackjack 상태와 비교했을 때, 패배 한다.")
    @Test
    void judgeMatchStatusWithBlackjackTest() {
        final BlackjackState otherState = BlackjackState.of(SPADE_ACE, SPADE_TEN);
        assertThat(STAND_STATE.judgeMatchStatus(otherState)).isEqualTo(MatchStatus.LOSS);
    }

    @DisplayName("Stand 상태는 Bust 상태와 비교했을 때, 승리 한다.")
    @Test
    void judgeMatchStatusWithBustTest() {
        final BustState otherState = BustState.of(SPADE_QUEEN, SPADE_TEN, SPADE_TWO);
        assertThat(STAND_STATE.judgeMatchStatus(otherState)).isEqualTo(MatchStatus.WIN);
    }

    @DisplayName("Stand 상태는 Stand 상태와 비교하여 승패를 계산할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 결과 : {2}, 카드1 : {0}, 카드2 : {1}")
    @MethodSource("blackjack.domain.participant.state.provider.StandStateTestProvider#provideForJudgeMatchStatusWithStandStateTest")
    void judgeMatchStatusWithStandStateTest(final List<Card> thisCards,
                                            final List<Card> otherCards,
                                            final MatchStatus expectedMatchStatus) {
        final StandState thisState = StandState.from(thisCards);
        final StandState otherState = StandState.from(otherCards);

        assertThat(thisState.judgeMatchStatus(otherState)).isEqualTo(expectedMatchStatus);
    }

    @DisplayName("턴이 종료되었는지 확인할 수 있어야 한다.")
    @Test
    void isFinishedTest() {
        assertThat(STAND_STATE.isFinished()).isTrue();
    }

    @DisplayName("Blackjack 상태인지 확인할 수 있어야 한다.")
    @Test
    void isNotBlackjackTest() {
        assertThat(STAND_STATE.isBlackjack()).isFalse();
    }

    @DisplayName("Bust 상태인지 확인할 수 있어야 한다.")
    @Test
    void isBustTest() {
        assertThat(STAND_STATE.isBust()).isFalse();
    }

    @DisplayName("카드의 합계를 확인할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 합계 : {1}, 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.StandStateTestProvider#provideForGetScoreTest")
    void getScoreTest(final List<Card> cards, final int expectedScore) {
        final State state = StandState.from(cards);
        final int actualScore = state.getScore();
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @DisplayName("보유한 카드를 확인할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.StandStateTestProvider#provideForGetCardsTest")
    void getCardsTest(final List<Card> expectedCards) {
        final State state = StandState.from(expectedCards);
        final List<Card> actualCards = state.getCards();
        assertThat(actualCards).isEqualTo(expectedCards);
    }

}

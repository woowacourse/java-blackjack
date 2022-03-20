package blackjack.domain.participant.state;

import static blackjack.Fixture.SPADE_ACE;
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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.result.MatchStatus;

class BustStateTest {

    private static final BustState BUST_STATE = BustState.of(SPADE_TEN, SPADE_FIVE, SPADE_KING);

    @DisplayName("Bust 상태가 되기 위해서는 최소 2장의 카드가 준비되어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.BustStateTestProvider#provideForCardSizeExceptionTest")
    void cardSizeExceptionTest(final List<Card> cards) {
        assertThatThrownBy(() -> BustState.from(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("카드는 2장 이상이어야 합니다.");
    }

    @DisplayName("Bust 상태가 되기 위해서는 준비된 카드의 합이 21 초과이어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.BustStateTestProvider#provideForCardScoreExceptionTest")
    void cardScoreExceptionTest(final List<Card> cards) {
        assertThatThrownBy(() -> BustState.from(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("합계가 21 초과여야 Bust 상태가 될 수 있습니다.");
    }

    @DisplayName("Bust 상태는 Blackjack 상태와 비교했을 때, 패배 한다.")
    @Test
    void judgeMatchStatusWithBlackjackTest() {
        final BlackjackState otherState = BlackjackState.of(SPADE_ACE, SPADE_TEN);
        final MatchStatus actualMatchStatus = BUST_STATE.judgeMatchStatus(otherState);
        assertThat(actualMatchStatus).isEqualTo(MatchStatus.LOSS);
    }

    @DisplayName("Bust 상태는 Bust 상태와 비교했을 때, 패배 한다.")
    @Test
    void judgeMatchStatusWithBustTest() {
        final BustState otherState = BustState.of(SPADE_QUEEN, SPADE_TEN, SPADE_TWO);
        final MatchStatus actualMatchStatus = BUST_STATE.judgeMatchStatus(otherState);
        assertThat(actualMatchStatus).isEqualTo(MatchStatus.LOSS);
    }

    @DisplayName("Bust 상태는 Stand 상태와 비교했을 때, 패배 한다.")
    @Test
    void judgeMatchStatusWithStandTest() {
        final StandState otherState = StandState.of(SPADE_QUEEN, SPADE_FIVE, SPADE_TWO);
        final MatchStatus actualMatchStatus = BUST_STATE.judgeMatchStatus(otherState);
        assertThat(actualMatchStatus).isEqualTo(MatchStatus.LOSS);
    }

    @DisplayName("턴이 종료되었는지 확인할 수 있어야 한다.")
    @Test
    void isFinishedTest() {
        assertThat(BUST_STATE.isFinished()).isTrue();
    }

    @DisplayName("Blackjack 상태인지 확인할 수 있어야 한다.")
    @Test
    void isNotBlackjackTest() {
        assertThat(BUST_STATE.isBlackjack()).isFalse();
    }

    @DisplayName("Bust 상태인지 확인할 수 있어야 한다.")
    @Test
    void isBustTest() {
        assertThat(BUST_STATE.isBust()).isTrue();
    }

    @DisplayName("카드의 합계를 확인할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 합계 : {1}, 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.BustStateTestProvider#provideForGetScoreTest")
    void getScoreTest(final List<Card> cards, final int expectedScore) {
        final State state = BustState.from(cards);
        final int actualScore = state.getScore();
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @DisplayName("보유한 카드를 확인할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.BustStateTestProvider#provideForGetCardsTest")
    void getCardsTest(final List<Card> expectedCards) {
        final State state = BustState.from(expectedCards);
        final List<Card> actualCards = state.getCards();
        assertThat(actualCards).isEqualTo(expectedCards);
    }

}

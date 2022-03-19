package blackjack.domain.participant.state;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_NINE;
import static blackjack.Fixture.SPADE_TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.result.MatchStatus;

class BlackjackStateTest {

    private static final BlackjackState BLACKJACK_STATE = BlackjackState.of(SPADE_ACE, SPADE_TEN);

    @DisplayName("Blackjack 상태가 되기 위해서는 2장의 카드가 준비되어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.BlackjackStateTestProvider#provideForCardSizeExceptionTest")
    void cardSizeExceptionTest(final List<Card> cards) {
        assertThatThrownBy(() -> BlackjackState.from(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("카드는 2장 이어야 합니다.");
    }

    @DisplayName("Blackjack 상태가 되기 위해서는 준비된 카드의 합이 21 이어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.BlackjackStateTestProvider#provideForCardScoreExceptionTest")
    void cardScoreExceptionTest(final List<Card> cards) {
        assertThatThrownBy(() -> BlackjackState.from(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("합계가 21 이어야 Blackjack 상태가 될 수 있습니다.");
    }

    @DisplayName("Blackjack 상태는 Blackjack 상태와 비교했을 때, 무승부 이다.")
    @Test
    void judgeMatchStatusWithBlackjackTest() {
        final BlackjackState otherState = BlackjackState.of(SPADE_ACE, SPADE_TEN);
        assertThat(BLACKJACK_STATE.judgeMatchStatus(otherState)).isEqualTo(MatchStatus.DRAW);
    }

    @DisplayName("Blackjack 상태는 Blackjack이 아닌 상태와 비교했을 때, 무조건 Blackjack으로 승리 한다.")
    @Test
    void judgeMatchStatusWithNotBlackjackTest() {
        final StandState otherState = StandState.of(SPADE_ACE, SPADE_NINE);
        assertThat(BLACKJACK_STATE.judgeMatchStatus(otherState)).isEqualTo(MatchStatus.BLACKJACK);
    }

    @DisplayName("턴이 종료되었는지 확인할 수 있어야 한다.")
    @Test
    void isFinishedTest() {
        assertThat(BLACKJACK_STATE.isFinished()).isTrue();
    }

    @DisplayName("Blackjack 상태인지 확인할 수 있어야 한다.")
    @Test
    void isNotBlackjackTest() {
        assertThat(BLACKJACK_STATE.isBlackjack()).isTrue();
    }

    @DisplayName("Bust 상태인지 확인할 수 있어야 한다.")
    @Test
    void isBustTest() {
        assertThat(BLACKJACK_STATE.isBust()).isFalse();
    }

    @DisplayName("카드의 합계를 확인할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 합계 : {1}, 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.BlackjackStateTestProvider#provideForGetScoreTest")
    void getScoreTest(final List<Card> cards, final int expectedScore) {
        final State state = BlackjackState.from(cards);
        final int actualScore = state.getScore();
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @DisplayName("보유한 카드를 확인할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.BlackjackStateTestProvider#provideForGetCardsTest")
    void getCardsTest(final List<Card> expectedCards) {
        final State state = BlackjackState.from(expectedCards);
        final List<Card> actualCards = state.getCards();
        assertThat(actualCards).isEqualTo(expectedCards);
    }

}

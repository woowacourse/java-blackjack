package blackjack.domain.participant.state;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_NINE;
import static blackjack.Fixture.SPADE_TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.result.MatchStatus;

class BlackjackStateTest {

    @DisplayName("Blackjack 상태가 되기 위해서는 최소 2장의 카드가 준비되어야 한다.")
    @Test
    void cardSizeExceptionTest() {
        assertThatThrownBy(() -> BlackjackState.of(SPADE_TEN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("카드는 2장 이상이어야 합니다.");
    }

    @DisplayName("Blackjack 상태가 되기 위해서는 준비된 카드의 합이 21 이어야 한다.")
    @Test
    void cardScoreExceptionTest() {
        assertThatThrownBy(() -> BlackjackState.of(SPADE_KING, SPADE_TEN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("합계가 21 이어야 Blackjack 상태가 될 수 있습니다.");
    }

    @DisplayName("Blackjack 상태는 Blackjack 상태와 비교했을 때, 무승부 이다.")
    @Test
    void judgeMatchStatusWithBlackjackTest() {
        final BlackjackState thisState = BlackjackState.of(SPADE_ACE, SPADE_TEN);
        final BlackjackState otherState = BlackjackState.of(SPADE_ACE, SPADE_TEN);

        assertThat(thisState.judgeMatchStatus(otherState)).isEqualTo(MatchStatus.DRAW);
    }

    @DisplayName("Blackjack 상태는 Blackjack이 아닌 상태와 비교했을 때, 무조건 Blackjack으로 승리 한다.")
    @Test
    void judgeMatchStatusWithNotBlackjackTest() {
        final BlackjackState thisState = BlackjackState.of(SPADE_ACE, SPADE_TEN);
        final StandState otherState = StandState.of(SPADE_ACE, SPADE_NINE);

        assertThat(thisState.judgeMatchStatus(otherState)).isEqualTo(MatchStatus.BLACKJACK);
    }

    @DisplayName("턴이 종료되었는지 확인할 수 있어야 한다.")
    @Test
    void isFinishedTest() {
        final State state = BlackjackState.of(SPADE_ACE, SPADE_TEN);
        assertThat(state.isFinished()).isTrue();
    }

    @DisplayName("Blackjack 상태인지 확인할 수 있어야 한다.")
    @Test
    void isNotBlackjackTest() {
        final State state = BlackjackState.of(SPADE_ACE, SPADE_TEN);
        assertThat(state.isBlackjack()).isTrue();
    }

    @DisplayName("Bust 상태인지 확인할 수 있어야 한다.")
    @Test
    void isBustTest() {
        final State state = BlackjackState.of(SPADE_ACE, SPADE_TEN);
        assertThat(state.isBust()).isFalse();
    }

}

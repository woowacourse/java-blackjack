package blackjack.domain.participant.state;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_FIVE;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_QUEEN;
import static blackjack.Fixture.SPADE_TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.result.MatchStatus;

class BustStateTest {

    @DisplayName("Bust 상태가 되기 위해서는 최소 2장의 카드가 준비되어야 한다.")
    @Test
    void cardSizeExceptionTest() {
        assertThatThrownBy(() -> BustState.of(SPADE_TEN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("카드는 2장 이상이어야 합니다.");
    }

    @DisplayName("Bust 상태가 되기 위해서는 준비된 카드의 합이 21 초과이어야 한다.")
    @Test
    void cardScoreExceptionTest() {
        assertThatThrownBy(() -> BustState.of(SPADE_KING, SPADE_QUEEN, SPADE_ACE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("합계가 21 초과여야 Bust 상태가 될 수 있습니다.");
    }

    @DisplayName("Bust 상태는 무조건 패배한다.")
    @Test
    void judgeMatchStatusTest() {
        final BustState thisState = BustState.of(SPADE_TEN, SPADE_FIVE, SPADE_KING);
        final BustState otherState = BustState.of(SPADE_TEN, SPADE_FIVE, SPADE_KING);

        assertThat(thisState.judgeMatchStatus(otherState)).isEqualTo(MatchStatus.LOSS);
    }

    @DisplayName("턴이 종료되었는지 확인할 수 있어야 한다.")
    @Test
    void isFinishedTest() {
        final State state = BustState.of(SPADE_TEN, SPADE_FIVE, SPADE_KING);
        assertThat(state.isFinished()).isTrue();
    }

    @DisplayName("Blackjack 상태인지 확인할 수 있어야 한다.")
    @Test
    void isNotBlackjackTest() {
        final State state = BustState.of(SPADE_TEN, SPADE_FIVE, SPADE_KING);
        assertThat(state.isBlackjack()).isFalse();
    }

    @DisplayName("Bust 상태인지 확인할 수 있어야 한다.")
    @Test
    void isBustTest() {
        final State state = BustState.of(SPADE_TEN, SPADE_FIVE, SPADE_KING);
        assertThat(state.isBust()).isTrue();
    }

}

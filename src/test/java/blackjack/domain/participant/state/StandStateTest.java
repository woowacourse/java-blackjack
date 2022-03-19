package blackjack.domain.participant.state;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_NINE;
import static blackjack.Fixture.SPADE_QUEEN;
import static blackjack.Fixture.SPADE_TEN;
import static blackjack.Fixture.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.result.MatchStatus;

class StandStateTest {

    @DisplayName("Stand 상태가 되기 위해서는 최소 2장의 카드가 준비되어야 한다.")
    @Test
    void cardSizeExceptionTest() {
        assertThatThrownBy(() -> StandState.of(SPADE_TEN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("카드는 2장 이상이어야 합니다.");
    }

    @DisplayName("Stand 상태가 되기 위해서는 준비된 카드의 합이 21 이하이어야 한다.")
    @Test
    void cardScoreExceptionTest() {
        assertThatThrownBy(() -> StandState.of(SPADE_KING, SPADE_QUEEN, SPADE_TWO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("합계가 21 이하여야 Stand 상태가 될 수 있습니다.");
    }

    @DisplayName("Stand 상태는 Blackjack 상태와 비교했을 때, 패배 한다.")
    @Test
    void judgeMatchStatusWithBlackjackTest() {
        final StandState thisState = StandState.of(SPADE_KING, SPADE_TEN);
        final BlackjackState otherState = BlackjackState.of(SPADE_ACE, SPADE_TEN);

        assertThat(thisState.judgeMatchStatus(otherState)).isEqualTo(MatchStatus.LOSS);
    }

    @DisplayName("Stand 상태는 Bust 상태와 비교했을 때, 승리 한다.")
    @Test
    void judgeMatchStatusWithBustTest() {
        final StandState thisState = StandState.of(SPADE_KING, SPADE_TEN);
        final BustState otherState = BustState.of(SPADE_QUEEN, SPADE_TEN, SPADE_TWO);

        assertThat(thisState.judgeMatchStatus(otherState)).isEqualTo(MatchStatus.WIN);
    }

    @DisplayName("Stand 상태는 Stand 상태와 비교하여 승패를 계산할 수 있어야 한다.")
    @ParameterizedTest
    @MethodSource("provideForJudgeMatchStatusWithStandStateTest")
    void judgeMatchStatusWithStandStateTest(final List<Card> thisCards,
                                            final List<Card> otherCards,
                                            final MatchStatus expectedMatchStatus) {
        final StandState thisState = StandState.from(thisCards);
        final StandState otherState = StandState.from(otherCards);

        assertThat(thisState.judgeMatchStatus(otherState)).isEqualTo(expectedMatchStatus);
    }

    private static Stream<Arguments> provideForJudgeMatchStatusWithStandStateTest() {
        return Stream.of(
                Arguments.of(
                        List.of(SPADE_KING, SPADE_NINE, SPADE_TWO),
                        List.of(SPADE_KING, SPADE_NINE, SPADE_ACE),
                        MatchStatus.WIN
                ),
                Arguments.of(
                        List.of(SPADE_KING, SPADE_NINE, SPADE_TWO),
                        List.of(SPADE_KING, SPADE_NINE, SPADE_TWO),
                        MatchStatus.DRAW
                ),
                Arguments.of(
                        List.of(SPADE_KING, SPADE_NINE, SPADE_ACE),
                        List.of(SPADE_KING, SPADE_NINE, SPADE_TWO),
                        MatchStatus.LOSS
                )
        );
    }

    @DisplayName("턴이 종료되었는지 확인할 수 있어야 한다.")
    @Test
    void isFinishedTest() {
        final State state = StandState.of(SPADE_KING, SPADE_TEN);
        assertThat(state.isFinished()).isTrue();
    }

    @DisplayName("Blackjack 상태인지 확인할 수 있어야 한다.")
    @Test
    void isNotBlackjackTest() {
        final State state = StandState.of(SPADE_KING, SPADE_TEN);
        assertThat(state.isBlackjack()).isFalse();
    }

    @DisplayName("Bust 상태인지 확인할 수 있어야 한다.")
    @Test
    void isBustTest() {
        final State state = StandState.of(SPADE_KING, SPADE_TEN);
        assertThat(state.isBust()).isFalse();
    }

}

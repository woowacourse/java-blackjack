package blackjack.blackjack.state.running;

import static blackjack.fixture.TestFixture.provide16Cards;
import static blackjack.fixture.TestFixture.provideBlackjack;
import static blackjack.fixture.TestFixture.provideBustCards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.StateType;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerRunningTest {

    @Nested
    class initialStateTest {

        @Test
        void 초기_카드가_블랙잭이면_블랙잭_상태를_반환한다() {
            // Given
            State state = PlayerRunning.initialState(provideBlackjack());

            // When & Then
            assertThat(state.getStateType()).isEqualTo(StateType.BLACKJACK);
        }

        @Test
        void 초기_카드가_블랙잭이_아니면_현재_상태를_반환한다() {
            // Given
            State state = PlayerRunning.initialState(provide16Cards());

            // When & Then
            assertThat(state.getStateType()).isEqualTo(StateType.RUNNING);
        }
    }

    @Nested
    class receiveCardTest {

        State state = PlayerRunning.initialState(new Hand(List.of()));

        @Test
        void 카드가_버스트이면_버스트_상태를_반환한다() {
            // Given

            // When & Then
            assertThat(state.receiveCards(provideBustCards()).getStateType()).isEqualTo(StateType.BUST);
        }
    }
}

package blackjack.blackjack.state.running;

import static blackjack.fixture.TestFixture.provide16Cards;
import static blackjack.fixture.TestFixture.provideBlackjack;
import static blackjack.fixture.TestFixture.provideBustCards;
import static blackjack.fixture.TestFixture.provideOver16Cards;
import static blackjack.fixture.TestFixture.provideUnder16Cards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.StateType;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerRunningTest {

    @Nested
    class initialStateTest {

        @Test
        void 초기_카드가_블랙잭이면_블랙잭_상태를_반환한다() {
            // Given
            State state = DealerRunning.initialState(provideBlackjack());

            // When & Then
            assertThat(state.getStateType()).isEqualTo(StateType.BLACKJACK);
        }

        @Test
        void 초기_카드가_16초과이면_stay_상태를_반환한다() {
            // Given
            State state = DealerRunning.initialState(provideOver16Cards());

            // When & Then
            assertThat(state.getStateType()).isEqualTo(StateType.STAY);
        }

        @Test
        void 초기_카드가_16이하이면_현재_상태를_반환한다() {
            // Given
            State state = DealerRunning.initialState(provideUnder16Cards());

            // When & Then
            assertThat(state.getStateType()).isEqualTo(StateType.RUNNING);
        }
    }

    @Nested
    class receiveCardTest {

        private State state;

        @BeforeEach
        void setUp() {
            state = DealerRunning.initialState(new Hand(List.of()));
        }

        @Test
        void 카드가_버스트이면_버스트_상태를_반환한다() {
            // Given

            // When & Then
            assertThat(state.receiveCards(provideBustCards()).getStateType()).isEqualTo(StateType.BUST);
        }

        @Test
        void 카드가_16_초과이면_stay_상태를_반환한다() {
            // Given

            // When & Then
            assertThat(state.receiveCards(provideOver16Cards()).getStateType()).isEqualTo(StateType.STAY);
        }

        @Test
        void 카드가_16_이하이면_현재_상태를_반환한다() {
            // Given

            // When & Then
            assertThat(state.receiveCards(provide16Cards()).getStateType()).isEqualTo(StateType.RUNNING);
        }
    }
}

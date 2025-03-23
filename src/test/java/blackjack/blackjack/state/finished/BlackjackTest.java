package blackjack.blackjack.state.finished;

import static blackjack.fixture.TestFixture.provideBlackjack;
import static blackjack.fixture.TestFixture.provideUnder16Cards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.blackjack.state.State;
import blackjack.blackjack.state.running.DealerRunning;
import blackjack.blackjack.state.running.PlayerRunning;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    private State state;

    @BeforeEach
    void setUp() {
        state = PlayerRunning.initialState(provideBlackjack());
    }

    @Test
    void 수익을_계산한다() {
        // Given
        BigDecimal bettingAmount = BigDecimal.valueOf(3_000);
        State dealerState = DealerRunning.initialState(provideUnder16Cards());

        // When & Then
        assertThat(state.calculateProfit(bettingAmount, dealerState)).isEqualTo(new BigDecimal("4500.0"));
    }
}

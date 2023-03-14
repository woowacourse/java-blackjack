package domain.state;

import org.junit.jupiter.api.Test;

import static domain.Fixtures.SPADE_ACE;
import static domain.Fixtures.SPADE_TEN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackjackTest {

    @Test
    void blackjack() {
        final var state = new Ready()
                .draw(SPADE_ACE)
                .draw(SPADE_TEN);

        assertThat(state).isInstanceOf(Blackjack.class);
    }
}

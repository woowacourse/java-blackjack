package domain.state;

import org.junit.jupiter.api.Test;

import static domain.Fixtures.SPADE_TEN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StayTest {

    @Test
    void stay() {
        final var state = new Ready()
                .draw(SPADE_TEN)
                .draw(SPADE_TEN);

        assertThat(state.stay()).isInstanceOf(Stay.class);
    }
}

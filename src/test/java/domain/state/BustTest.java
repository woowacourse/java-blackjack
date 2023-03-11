package domain.state;

import org.junit.jupiter.api.Test;

import static domain.Fixtures.HEART_TEN;
import static domain.Fixtures.SPADE_TEN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BustTest {

    @Test
    void bust() {
        final var state = new Ready()
                .draw(SPADE_TEN)
                .draw(HEART_TEN)
                .draw(SPADE_TEN);

        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    void bustDraw_Throw() {
        final var state = new Ready()
                .draw(SPADE_TEN)
                .draw(SPADE_TEN)
                .draw(SPADE_TEN);

        assertThatThrownBy(() -> state.draw(SPADE_TEN))
                .isInstanceOf(IllegalStateException.class);
    }
}

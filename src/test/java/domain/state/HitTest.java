package domain.state;

import org.junit.jupiter.api.Test;

import static domain.Fixtures.SPADE_THREE;
import static domain.Fixtures.SPADE_TWO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HitTest {

    @Test
    void hit() {
        final var state = new Ready()
                .draw(SPADE_TWO)
                .draw(SPADE_THREE);

        assertThat(state).isInstanceOf(Hit.class);
    }

}

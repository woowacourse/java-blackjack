package blackjack.domain.state;

import org.junit.jupiter.api.Test;

import static blackjack.domain.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

class StateFactoryTest {
    @Test
    void hit() {
        final State state = StateFactory.draw(CLUBS_KING, CLUBS_TEN);
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    void blackjack() {
        final State state = StateFactory.draw(CLUBS_KING, CLUBS_ACE);
        assertThat(state).isInstanceOf(Blackjack.class);
    }
}

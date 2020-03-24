package blackjack.domain.state;

import org.junit.jupiter.api.Test;

import static blackjack.domain.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

class HitTest {
    @Test
    void draw() {
        final Hit hit = new Hit(new Cards(CLUBS_ACE, CLUBS_TWO));
        final State state = hit.draw(CLUBS_TEN);
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    void bust() {
        final Hit hit = new Hit(new Cards(CLUBS_TEN, CLUBS_TWO));
        final State state = hit.draw(CLUBS_KING);
        assertThat(state).isInstanceOf(Bust.class);
    }
}

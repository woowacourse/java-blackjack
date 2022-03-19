package blackjack_statepattern.state;

import static blackjack_statepattern.Fixture.SPADES_JACK;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StayTest {

    @Test
    @DisplayName("스테이 상태에서 스테이 할 수 없다.")
    void stay() {
        State state = new Stay();

        assertThrows(IllegalArgumentException.class, state::stay);
    }

    @Test
    @DisplayName("스테이 상태에서 드로우 할 수 없다.")
    void draw() {
        State state = new Stay();
        assertThrows(IllegalArgumentException.class, () -> state.draw(SPADES_JACK));
    }
}

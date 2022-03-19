package blackjack_statepattern;

import static blackjack_statepattern.Fixture.SPADES_ACE;
import static blackjack_statepattern.Fixture.SPADES_JACK;
import static blackjack_statepattern.Fixture.SPADES_TEN;
import static blackjack_statepattern.Fixture.SPADES_TWO;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void hit() {
        final var state = Game.start(SPADES_JACK, SPADES_TWO);
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    void hit_and_draw() {
        var state = Game.start(SPADES_JACK, SPADES_TWO);
        state.draw(SPADES_ACE);
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    void hit_and_bust() {
        var state = Game.start(SPADES_JACK, SPADES_TWO)
                .draw(SPADES_TEN);
        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    void blackjack() {
        final var state = Game.start(SPADES_JACK, SPADES_ACE);
        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    void blackjackDraw() {
        final var state = Game.start(SPADES_JACK, SPADES_ACE);
        assertThatThrownBy(
                () -> state.draw(Card.of(Suit.SPADES, Denomination.TEN))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}

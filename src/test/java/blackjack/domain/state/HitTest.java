package blackjack.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static blackjack.domain.FixtureCards.ACE_CLUBS;
import static blackjack.domain.FixtureCards.JACK_SPADES;
import static org.assertj.core.api.Assertions.assertThat;

class HitTest {
    Hit hit;

    @BeforeEach
    void setUp() {
        hit = new Hit(new Cards(JACK_SPADES, JACK_SPADES));
    }

    @Test
    void Hit() {
        State state = hit.draw(ACE_CLUBS);
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    void Bust() {
        State state = hit.draw(JACK_SPADES);
        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    void Stay() {
        State state = hit.stay();
        assertThat(state).isInstanceOf(Stay.class);
    }
}
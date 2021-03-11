package blackjack.domain.status;

import org.junit.jupiter.api.Test;

import static blackjack.domain.FixtureCards.*;
import static org.assertj.core.api.Assertions.assertThat;

class StateFactoryTest {
    @Test
    void Blackjack() {
        State state = StateFactory.draw(ACE_CLUBS, JACK_SPADES);
        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    void Bust() {
        State state = StateFactory.draw(JACK_SPADES, JACK_SPADES, JACK_SPADES);
        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    void Hit() {
        State state = StateFactory.draw(JACK_SPADES,JACK_SPADES);
        assertThat(state).isInstanceOf(Hit.class);
    }
}
package blackjack.domain.status;

import org.junit.jupiter.api.Test;

import static blackjack.domain.FixtureCards.*;
import static org.assertj.core.api.Assertions.assertThat;

class StatusFactoryTest {
    @Test
    void Blackjack() {
        Status status = StatusFactory.draw(ACE_CLUBS, JACK_SPADES);
        assertThat(status).isInstanceOf(Blackjack.class);
    }

    @Test
    void Bust() {
        Status status = StatusFactory.draw(JACK_SPADES, JACK_SPADES, JACK_SPADES);
        assertThat(status).isInstanceOf(Bust.class);
    }

    @Test
    void Hit() {
        Status status = StatusFactory.draw(JACK_SPADES,JACK_SPADES);
        assertThat(status).isInstanceOf(Hit.class);
    }
}
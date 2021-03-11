package blackjack.domain.status;

import org.junit.jupiter.api.Test;

import static blackjack.domain.FixtureCards.*;
import static org.assertj.core.api.Assertions.assertThat;

class StartRoundTest {
    @Test
    void Blackjack() {
        State state = StartRound.draw(ACE_CLUBS, JACK_SPADES);
        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    void Hit() {
        State state = StartRound.draw(JACK_SPADES,JACK_SPADES);
        assertThat(state).isInstanceOf(Hit.class);
    }
}
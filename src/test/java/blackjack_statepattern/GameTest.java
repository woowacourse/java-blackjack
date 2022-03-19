package blackjack_statepattern;

import static blackjack_statepattern.Fixture.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void hit() {
        final var hit = Game.start(SPADES_JACK, SPADES_TWO);
        Assertions.assertThat(hit).isInstanceOf(Hit.class);
    }

    @Test
    void blackJack() {
        final var blackjack = Game.start(SPADES_JACK, SPADES_ACE);
        Assertions.assertThat(blackjack).isInstanceOf(Blackjack.class);
    }
}

package blackjack_statepattern;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void start() {
        //카드 2장을 넣어서 Blackjack or Hit

        //Hit
        final var hit = Game.start(Fixture.SPADES_JACK, Fixture.SPADES_TWO);
        Assertions.assertThat(hit).isInstanceOf(Hit.class);
    }
}

package blackjack.domain.state;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static blackjack.domain.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FirstTurnTest {
    @Test
    void blackjack() {
        final State blackjack = FirstTurn.draw(Arrays.asList(CLUB_ACE, CLUB_TEN));
        assertThat(blackjack).isInstanceOf(Blackjack.class);
    }

    @Test
    void hit() {
        final State hit = FirstTurn.draw(Arrays.asList(CLUB_TWO, CLUB_TEN));
        assertThat(hit).isInstanceOf(Hit.class);
    }
}

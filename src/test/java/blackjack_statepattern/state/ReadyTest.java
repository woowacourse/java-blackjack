package blackjack_statepattern.state;

import static blackjack_statepattern.Fixture.SPADES_ACE;
import static blackjack_statepattern.Fixture.SPADES_JACK;
import static blackjack_statepattern.Fixture.SPADES_TWO;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @Test
    @DisplayName("카드를 한장만 받았을 때는 여전히 Ready 상태")
    void ready() {
        State state = new Ready().draw(SPADES_ACE);
        assertThat(state).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("카드를 두장받고 21이 아닐 경우 Hit 상태")
    void hit() {
        State state = new Ready().draw(SPADES_JACK)
                .draw(SPADES_TWO);
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드를 두장받고 합이 21인 경우 Blackjack상태")
    void blackjack() {
        State state = new Ready().draw(SPADES_JACK)
                .draw(SPADES_ACE);
        assertThat(state).isInstanceOf(Blackjack.class);
    }
}

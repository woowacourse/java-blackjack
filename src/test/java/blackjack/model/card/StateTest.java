package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.state.Blackjack;
import blackjack.model.state.Hit;
import blackjack.model.state.Ready;
import blackjack.model.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StateTest {

    @DisplayName("카드를 한장만 가지고 있으면 Ready이다.")
    @Test
    void ready() {
        State state = new Ready();
        state = state.add(new Card(CardNumber.EIGHT, Symbol.CLOVER));

        assertThat(state).isInstanceOf(Ready.class);
    }

    @DisplayName("카드를 두장 가지고 있을때 카드 숫자 합이 21이 아니면 Hit이다.")
    @Test
    void hit() {
        State state = new Ready();
        state = state.add(new Card(CardNumber.EIGHT, Symbol.CLOVER));
        state = state.add(new Card(CardNumber.TWO, Symbol.CLOVER));

        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("카드를 두장 가지고 있을때 카드 숫자 합이 21이면 Blackjack이다.")
    @Test
    void blackjack() {
        State state = new Ready();
        state = state.add(new Card(CardNumber.JACK, Symbol.CLOVER));
        state = state.add(new Card(CardNumber.ACE, Symbol.CLOVER));

        assertThat(state).isInstanceOf(Blackjack.class);
    }
}

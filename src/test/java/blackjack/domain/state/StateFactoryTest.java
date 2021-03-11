package blackjack.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.state.CardFactory.SPADE_JACK;
import static blackjack.domain.state.CardFactory.SPADE_TEN;
import static org.assertj.core.api.Assertions.assertThat;

class StateFactoryTest {

    @DisplayName("카드의 숫자의 합이 21이하면 hit상태를 반환한다.")
    @Test
    void drawHitTest() {
        State state = StateFactory.draw(SPADE_JACK, SPADE_TEN);
        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("카드의 숫자의 합이 21이하면 hit상태를 반환한다.")
    @Test
    void drawTest() {
        State state = StateFactory.draw(SPADE_JACK, SPADE_TEN);
        assertThat(state).isInstanceOf(Hit.class);
    }
}
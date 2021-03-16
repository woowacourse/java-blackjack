package blackjack.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.state.CardFactory.*;
import static org.assertj.core.api.Assertions.assertThat;

class StateFactoryTest {
    @DisplayName("카드의 숫자의 합이 21이하면 hit상태를 반환한다.")
    @Test
    void drawHitTest() {
        State state = StateFactory.draw(SPADE_JACK, SPADE_TEN);
        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("카드의 숫자의 합이 21이면 BlackJack 상태를 반환한다.")
    @Test
    void drawBlackJackTest() {
        State state = StateFactory.draw(SPADE_ACE, SPADE_TEN);
        assertThat(state).isInstanceOf(BlackJack.class);
    }

    @DisplayName("2개의 카드가 에이스면 하나의 카드를 1로 계산한다.")
    @Test
    void drawAceTest() {
        State state = StateFactory.draw(SPADE_ACE, SPADE_ACE);
        assertThat(state).isInstanceOf(Hit.class);
    }
}

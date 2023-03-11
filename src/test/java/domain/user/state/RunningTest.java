package domain.user.state;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.FIVE;
import static domain.card.Denomination.FOUR;
import static domain.card.Denomination.JACK;
import static domain.card.Denomination.SIX;
import static domain.card.Denomination.TEN;
import static domain.card.Denomination.TWO;
import static domain.card.Suits.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {

    @DisplayName("카드를 받았는데, 총 합이 21점보다 작으면 그대로 Running상태이다.")
    @Test
    void stateToRunning() {
        State state = new Ready();
        state = state.draw(Card.of(FOUR, DIAMOND));
        state = state.draw(Card.of(SIX, DIAMOND)); // Ready -> Running
        state = state.draw(Card.of(TEN, DIAMOND));

        assertThat(state).isInstanceOf(Running.class);
    }

    @DisplayName("카드를 받았는데, 총 합이 21점보다 크면 Bust상태로 변한다.")
    @Test
    void stateToBust() {
        State state = new Ready();
        state = state.draw(Card.of(TEN, DIAMOND));
        state = state.draw(Card.of(JACK, DIAMOND)); // Ready -> Running
        state = state.draw(Card.of(TWO, DIAMOND));

        assertThat(state).isInstanceOf(Bust.class);
    }

    @DisplayName("카드를 받았는데, 총 합이 21점이면 Stay상태로 변한다.")
    @Test
    void stateToStay() {
        State state = new Ready();
        state = state.draw(Card.of(TEN, DIAMOND));
        state = state.draw(Card.of(JACK, DIAMOND)); // Ready -> Running
        state = state.draw(Card.of(ACE, DIAMOND));

        assertThat(state).isInstanceOf(Stay.class);
    }


    @DisplayName("카드를 더 받을 수 있다.")
    @Test
    void isDrawable() {
        State state = new Ready();
        state.draw(Card.of(FIVE, DIAMOND));
        state = state.draw(Card.of(SIX, DIAMOND)); // Ready -> Running

        assertThat(state).isInstanceOf(Running.class);
        assertThat(state.isDrawable()).isTrue();
    }
}

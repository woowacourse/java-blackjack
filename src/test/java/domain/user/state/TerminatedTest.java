package domain.user.state;

import static domain.card.Denomination.JACK;
import static domain.card.Denomination.KING;
import static domain.card.Denomination.QUEEN;
import static domain.card.Denomination.TEN;
import static domain.card.Suits.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TerminatedTest {

    @DisplayName("카드를 더 받으려 하면 예외를 반환한다.")
    @Test
    void drawException() {
        State state = new Ready();
        state = state.draw(Card.of(TEN, DIAMOND));
        state = state.draw(Card.of(JACK, DIAMOND)); // Ready -> Running
        State newState = state.draw(Card.of(QUEEN, DIAMOND)); // Running -> Bust
        assertThatThrownBy(() -> newState.draw(Card.of(KING, DIAMOND)))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("카드를 더 받을 수 없다.")
    @Test
    void isDrawable() {
        State state = new Ready();
        state = state.draw(Card.of(TEN, DIAMOND));
        state = state.draw(Card.of(JACK, DIAMOND)); // Ready -> Running
        state = state.draw(Card.of(QUEEN, DIAMOND)); // Running -> Bust
        assertThat(state.isDrawable()).isFalse();
    }
}

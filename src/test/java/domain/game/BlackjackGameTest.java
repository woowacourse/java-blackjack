package domain.game;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.state.Blackjack;
import domain.state.Bust;
import domain.state.Hit;
import domain.state.Ready;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.Fixtures.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


public class BlackjackGameTest {

    @Test
    void hit() {
        final var state = new Ready()
                .draw(SPADE_TWO)
                .draw(SPADE_THREE);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    void blackjack() {
        final var state = new Ready()
                .draw(SPADE_ACE)
                .draw(SPADE_TEN);

        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    void bust() {
        final var state = new Ready()
                .draw(SPADE_TEN)
                .draw(HEART_TEN)
                .draw(SPADE_TEN);

        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    void hitCards() {
        final var state = new Ready()
                .draw(SPADE_TEN)
                .draw(HEART_TEN);

        final var cards = state.cards();

        assertThat(cards).isEqualTo(List.of(SPADE_TEN,
                Card.of(Suit.HEART, Denomination.TEN)));
    }

    @Test
    void bustDraw_Throw() {
        final var state = new Ready()
                .draw(SPADE_TEN)
                .draw(SPADE_TEN)
                .draw(SPADE_TEN);

        assertThatThrownBy(() -> state.draw(SPADE_TEN))
                .isInstanceOf(IllegalStateException.class);
    }
}

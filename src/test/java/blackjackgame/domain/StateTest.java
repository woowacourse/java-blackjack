package blackjackgame.domain;

import static blackjackgame.domain.CardFixture.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.CardValue;
import blackjackgame.domain.card.Symbol;
import blackjackgame.domain.state.BlackJack;
import blackjackgame.domain.state.Bust;
import blackjackgame.domain.state.Hit;
import blackjackgame.domain.state.Ready;

public class StateTest {
    @Test
    void hit() {
        final var state = new Ready()
            .draw(CLOVER_TWO)
            .draw(CLOVER_THREE);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    void hitCards() {
        final var state = new Ready()
            .draw(CLOVER_TWO)
            .draw(CLOVER_THREE);

        final var cards = state.cards();

        assertThat(cards)
            .containsExactly(
                CLOVER_TWO,
                CLOVER_THREE);
    }

    @Test
    void Bust() {
        final var state = new Ready()
            .draw(CLOVER_KING)
            .draw(CLOVER_KING)
            .draw(CLOVER_NINE);

        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    void BustDraw() {
        final var state = new Ready()
            .draw(CLOVER_KING)
            .draw(CLOVER_KING)
            .draw(CLOVER_NINE);

        assertThatThrownBy(() -> state.draw(Card.of(Symbol.CLOVER, CardValue.NINE)))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void BlackJack() {
        final var state = new Ready()
            .draw(CLOVER_ACE)
            .draw(CLOVER_KING);

        assertThat(state).isInstanceOf(BlackJack.class);
    }
}

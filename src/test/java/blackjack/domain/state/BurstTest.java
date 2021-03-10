package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BurstTest {
    private Burst state;

    @BeforeEach
    void setUp() {
        state = new Burst();
    }

    @Test
    void checkFinished() {
        assertTrue(state.isFinished());
    }

    @Test
    void exception() {
        assertThatThrownBy(() -> state.keepContinue(true)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("옳지 않은 곳에서 호출");
    }
}
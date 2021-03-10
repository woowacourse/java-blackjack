package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StayTest {
    private PlayerState state;

    @BeforeEach
    void setUp() {
        Cards cards = new Cards();
        Card firstCard = new Card(CardPattern.DIAMOND, CardNumber.TEN);
        Card secondCard = new Card(CardPattern.DIAMOND, CardNumber.THREE);
        cards.addCard(firstCard);
        cards.addCard(secondCard);
        state = StateFactory.drawTwoCards(cards);
    }

    @Test
    void checkFinished() {
        state = state.keepContinue(false);
        assertTrue(state.isFinished());
    }

    @Test
    void exception() {
        state = state.keepContinue(false);
        assertThatThrownBy(() -> state.keepContinue(true)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("옳지 않은 곳에서 호출");
    }
}
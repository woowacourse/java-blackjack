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

class HitTest {
    private Cards cards;
    private PlayerState state;

    @BeforeEach
    void setUp() {
        cards = new Cards();
        Card firstCard = new Card(CardPattern.DIAMOND, CardNumber.TEN);
        Card secondCard = new Card(CardPattern.DIAMOND, CardNumber.THREE);
        cards.addCard(firstCard);
        cards.addCard(secondCard);
        state = StateFactory.drawTwoCards(cards);

    }

    @Test
    void checkHit() {
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    void checkFinished() {
        assertFalse(state.isFinished());
    }

    @Test
    void checkStay() {
        assertThat(state.keepContinue(false)).isInstanceOf(Stay.class);
    }

    @Test
    void checkHitAgain() {
        assertThat(state.keepContinue(true)).isInstanceOf(Hit.class);
    }

    @Test
    void checkBurst() {
        Hit hit = new Hit();
        hit.drawNewCard(cards, new Card(CardPattern.CLOVER, CardNumber.NINE));
    }
}
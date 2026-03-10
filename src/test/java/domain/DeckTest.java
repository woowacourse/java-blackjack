package domain;

import domain.card.Card;
import exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void 덱을_정상적으로_생성하는_경우() {
        // given
        Deck deck = new Deck();

        // when, then
        assertEquals(52, deck.getCardsSize());
    }

    @Test
    void 드로우가_정상적으로_실행되는_경우() {
        // given
        Deck deck = new Deck();

        // when
        Card card = deck.drawCard();

        // then
        assertEquals(51, deck.getCardsSize());
        assertFalse(deck.getCards().contains(card));
    }

    @Test
    void 드로우가_실패하는_경우() {
        // given
        Deck deck = new Deck(new ArrayList<>());

        // when, then
        assertThatThrownBy(deck::drawCard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(ErrorMessage.EMPTY_DECK.getMessage());
    }

}
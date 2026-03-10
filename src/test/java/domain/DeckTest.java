package domain;

import domain.card.Card;
import exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void 초기_덱을_생성하면_52장의_카드가_준비된다() {
        // given
        Deck deck = new Deck();

        // when, then
        assertEquals(52, deck.getCardsSize());
    }

    @Test
    void 덱에서_카드를_뽑으면_남은_카드_수가_1장_감소한다() {
        // given
        Deck deck = new Deck();

        // when
        Card card = deck.drawCard();

        // then
        assertEquals(51, deck.getCardsSize());
        assertFalse(deck.getCards().contains(card));
    }

    @Test
    void 남은_카드가_없는_빈_덱에서_카드를_뽑으면_예외가_발생한다() {
        // given
        Deck deck = new Deck(new ArrayList<>());

        // when, then
        assertThatThrownBy(deck::drawCard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(ErrorMessage.EMPTY_DECK.getMessage());
    }

}
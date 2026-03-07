package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    @DisplayName("카드를 한 장 뽑으면 null이 아닌 카드가 반환된다.")
    void drawCard_returnsCard() {
        // given
        Deck deck = new Deck();

        // when
        Card card = deck.drawCard();

        // then
        assertNotNull(card);
    }

    @Test
    @DisplayName("카드를 뽑으면 덱의 카드 수가 감소한다.")
    void drawCard_decreasesDeckSize() {
        // given
        Deck deck = new Deck();

        // when
        deck.drawCard();
        deck.drawCard();

        // then
        assertDoesNotThrow(deck::drawCard);
    }

    @Test
    @DisplayName("52장의 카드를 모두 뽑으면 예외가 발생한다.")
    void drawAllCards_thenThrowException() {
        // given
        Deck deck = new Deck();

        // when
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        // then
        assertThrows(IllegalArgumentException.class, deck::drawCard);
    }
}

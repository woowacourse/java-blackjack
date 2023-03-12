package domain;


import static org.junit.jupiter.api.Assertions.assertThrows;

import domain.deck.Deck;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    Deck deck = new Deck();

    @DisplayName("덱의 최대 카드 반환 가능 수는 52번이다.")
    @Test
    void generateCardsTest() {
        // given
        final int expectedDeckSize = 52;

        //  when
        for (int i = 0; i < expectedDeckSize; i++) {
            deck.popCard();
        }

        // then
        assertThrows(NoSuchElementException.class, deck::popCard);
    }
}

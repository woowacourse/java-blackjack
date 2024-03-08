package blackjack.model.deck;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.model.deck.Card;
import blackjack.model.deck.Score;
import blackjack.model.deck.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    @DisplayName("카드가 정상적으로 생성된다.")
    void createCard() {
        assertDoesNotThrow(() -> new Card(Shape.DIA, Score.NINE));
    }
}

package blackjack.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    @DisplayName("카드가 정상적으로 생성된다.")
    void createCard() {
        assertDoesNotThrow(() -> new Card(Shape.DIA, Score.NINE));
    }
}

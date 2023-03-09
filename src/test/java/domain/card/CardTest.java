package domain.card;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    @DisplayName("카드의 값과 모양으로 카드를 생성한다.")
    @Test
    void createTest() {
        assertDoesNotThrow(() -> new Card(Value.ACE, Shape.SPADE));
    }
}

package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    @DisplayName("카드를 생성한다.")
    void createCardTest() {
        Assertions.assertDoesNotThrow(()->new Card(CardNumber.ACE ,CardPattern.SPADE));
    }
}

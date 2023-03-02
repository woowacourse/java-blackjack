package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CardTest {
    @Test
    @DisplayName("카드를 생성한다.")
    void createCardTest() {
        assertDoesNotThrow(()->new Card(CardNumber.ACE ,CardPattern.SPADE));
    }

    @Test
    @DisplayName("카드의 숫자값을 가져온다.")
    void getCardValueTest() {
        Card card = new Card(CardNumber.ACE, CardPattern.SPADE);
        assertThat(card.getCardValue()).isEqualTo(11);
    }
}

package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardCalculatorTest {
    @DisplayName("카드의 합을 구한다.")
    @Test
    void sum() {
        // given
        CardCalculator cardCalculator = new CardCalculator();
        Card card1 = new Card(CardNumber.TWO, CardShape.HEART);
        Card card2 = new Card(CardNumber.EIGHT, CardShape.SPADE);
        Card card3 = new Card(CardNumber.ACE, CardShape.CLOVER);

        // when
        int result = cardCalculator.sum(card1, card2, card3);

        // then
        Assertions.assertThat(result).isEqualTo(21);
    }
}

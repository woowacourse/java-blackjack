package card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("해당하는 Number와 일치하는지 여부를 return 한다")
    @Test
    void isSameCardNumberTest() {
        Card card = new Card(CardNumber.ACE, CardPattern.DIA_PATTERN);

        Assertions.assertThat(card.isSameCardNumber(CardNumber.ACE)).isTrue();
    }
}

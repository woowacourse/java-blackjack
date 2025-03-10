package model.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("같은 숫자 카드인지 확인한다")
    @Test
    void sameCardTest() {
        Card card = new Card(CardNumber.TWO, CardShape.SPADE);
        assertAll(
                () -> assertThat(card.isSameNumber(CardNumber.TWO)).isTrue(),
                () -> assertThat(card.isSameNumber(CardNumber.KING)).isFalse()
        );
    }
}

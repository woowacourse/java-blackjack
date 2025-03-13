package model.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @DisplayName("해당 카드의 숫자를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "TWO, 2",
            "THREE, 3",
            "TEN, 10",
            "KING, 10",
            "ACE_ONE, 1"
    })
    void getNumberValueTest(final CardNumber cardNumber, final int value) {
        Card card = new Card(cardNumber, CardShape.SPADE);
        assertThat(card.getNumberValue()).isEqualTo(value);
    }
}

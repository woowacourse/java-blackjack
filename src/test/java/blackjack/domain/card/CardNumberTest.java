package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CardNumberTest {

    @ParameterizedTest
    @CsvSource({
            "JACK,10",
            "QUEEN,10",
            "KING,10",
    })
    @DisplayName("J, Q, K는 각각 10으로 계산한다")
    void cardNumberTest(CardNumber cardNumber, int expected) {
        // when
        int number = cardNumber.getNumber();

        // then
        assertThat(number).isEqualTo(expected);
    }
}

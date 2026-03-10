package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CardNumberTest {

    @DisplayName("CardNumber에 해당하지 않은 입력이 들어올 경우 IllegalArgumentException을 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "11", "12", "C", "B", "O"})
    void matchCardNumber_ValidInput_ThrowIllegalArgumentException(String value) {
        Assertions.assertThatThrownBy(() -> CardNumber.matchCardNumber(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("CardNumber 항목과 일치하는 입력이 들어올 경우 그에 맞는 CardNumber 항목을 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "A, ACE",
            "J, JACK",
            "Q, QUEEN",
            "K, KING",
            "2, TWO",
            "8, EIGHT"
    })
    void matchCardNumber_ValidInput_ReturnsCorrectEntry(String value, CardNumber cardNumber) {
        Assertions.assertThat(CardNumber.matchCardNumber(value))
                .isEqualTo(cardNumber);
    }
}

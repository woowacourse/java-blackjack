package domain;

import domain.card.CardNumber;
import domain.exception.OutOfBoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CardNumberTest {

    @DisplayName("숫자가 2 이상 10이하가 아닌 경우 OutOfBoundException 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "11", "12"})
    void rangeTest_numberNotBetween2and10_OutOfBoundException(String value) {
        Assertions.assertThatThrownBy(() -> CardNumber.matchCardNumber(value))
                .isInstanceOf(OutOfBoundException.class);
    }

    @DisplayName("A,J,Q,K가 아닌 문자는 IllegalArgumentException을 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"C", "B", "O"})
    void courtTest_notContainsAJQK_IllegalArgumentException(String value) {
        Assertions.assertThatThrownBy(() -> CardNumber.matchCardNumber(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력이 A, J, Q, K인 경우 CardNumber는 A를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "A, A",
            "J, J",
            "Q, Q",
            "K, K"
    })
    void courtTest_inputAJQK_A(String value, CardNumber cardNumber) {
        Assertions.assertThat(CardNumber.matchCardNumber(value))
                .isEqualTo(cardNumber);
    }
}

package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CardNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 14})
    @DisplayName("카드의 숫자를 반환하는 에러 테스트")
    void throw_cardTest(int input) {
        Assertions.assertThatThrownBy(() -> CardNumber.getCardNumber(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 13})
    @DisplayName("카드의 숫자를 반환하는 테스트")
    void cardTest(int input) {
        Assertions.assertThatCode(() -> CardNumber.getCardNumber(input)).doesNotThrowAnyException();
    }
}

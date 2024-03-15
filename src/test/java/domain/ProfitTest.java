package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProfitTest {

    @DisplayName("금액이 10의 배수가 아니면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {1_001, 10_001, 9})
    void notMultiplesOfTen(int amount) {
        Assertions.assertThatThrownBy(() -> new Profit(amount))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("[ERROR] 금액은 10의 배수만 가능합니다.");
    }
}

package blackjack.domain.money;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @ParameterizedTest
    @DisplayName("입력한 금액이 0 또는 음수일 때 예외 처리 확인")
    @ValueSource(ints = {0, -1})
    void zeroOrNegativeTest(int value) {
        Assertions.assertThatThrownBy(() -> new Money(value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("양수를 입력해 주세요.");
    }
}

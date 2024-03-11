package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class AmountTest {
    @DisplayName("유효하지 않은 배팅 금액이 입력되면 예외를 발생시킨다.")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"1million", "100000001", "-15000", "0"})
    void playerNameLengthTest(String invalidAmount) {
        // When & Then
        Assertions.assertThatThrownBy(() -> new Amount(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("배팅 금액은 100000000원 이하의 양의 정수만 가능합니다.");
    }
}

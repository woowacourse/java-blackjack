package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static domain.BettingAmount.MAX_AMOUNT;

public class BettingAmountTest {
    @DisplayName("유효하지 않은 배팅 금액이 입력되면 예외를 발생시킨다.")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"1million", "100000001", "-15000", "0"})
    void playerNameLengthTest(String invalidAmount) {
        // When & Then
        Assertions.assertThatThrownBy(() -> new BettingAmount(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("배팅 금액은 %d원 이하의 양의 정수만 가능합니다.", MAX_AMOUNT));
    }
}

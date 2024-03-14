package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BattingAmountTest {

    @Test
    @DisplayName("문자열을 통해서 배팅 금액을 생성한다.")
    void BattingAmount_Instance_create_with_Integer() {
        assertThatCode(() -> {
            new BattingAmount("1000");
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-10000", "abac", "", " "})
    @DisplayName("배팅 금액에 양수가 아닌 값이 들어오는 경우 예외처리된다.")
    void BattingAmount_Instance_only_create_with_positive_number(String value) {
        assertThatThrownBy(() -> {
            new BattingAmount(value);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}

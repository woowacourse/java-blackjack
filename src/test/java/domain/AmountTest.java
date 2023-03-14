package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class AmountTest {

    @DisplayName("1 이상의 금액이 아니면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void createAmountFail(int input) {
        Assertions.assertThatThrownBy(() -> new Amount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1 이상의 금액만 배팅이 가능합니다.");
    }

    @Test
    @DisplayName("금액을 정상적으로 생성한다.")
    void createAmountSuccess() {
        Amount amount = new Amount(10000);

        assertThat(amount.getValue()).isEqualTo(10000);
    }

}

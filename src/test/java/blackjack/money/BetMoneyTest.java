package blackjack.money;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetMoneyTest {

    @Test
    @DisplayName("1,000원으로 나누어떨어지지 않는 경우, 예외를 발생시킨다.")
    void notDivisibleByThousandAmountTest() {
        // when, then
        assertThatThrownBy(() -> BetMoney.of(1001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅은 1,000원 단위로만 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1_000, -2_000})
    @DisplayName("0 이하의 금액을 생성하려고 하는 경우 예외를 발생시킨다.")
    void negativeAmountTest(int amount) {
        // given
        assertThatThrownBy(() -> BetMoney.of(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 양의 정수여야 합니다.");
    }
}

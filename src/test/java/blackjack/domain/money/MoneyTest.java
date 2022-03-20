package blackjack.domain.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MoneyTest {

    @DisplayName("금액이 올바르게 들어올 경우 정상 실행된다.")
    @ParameterizedTest(name = "{index} {displayName} money = {0}")
    @ValueSource(ints = {1000, 15000, 3000})
    void RightInputMoneyTest(final int money) {
        assertDoesNotThrow(() -> new BetMoney(money));
    }

    @DisplayName("금액이 양의 정수가 아닐경우 예외를 발생시킨다.")
    @ParameterizedTest(name = "{index} {displayName} money = {0}")
    @ValueSource(ints = {-1, 0, -10000})
    void NonPositiveInputTest(final int money) {
        assertThatThrownBy(() -> new BetMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 투입 금액은 양의 정수여야 합니다.");
    }

    @DisplayName("금액이 1000원 단위의 금액이 아닌 경우 예외를 발생시킨다.")
    @ParameterizedTest(name = "{index} {displayName} money = {0}")
    @ValueSource(ints = {100, 900, 10800, 2009})
    void NotDivideByThousandInputTest(final int money) {
        assertThatThrownBy(() -> new BetMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 투입 금액은 1000원 단위로 넣어야 합니다.");
    }
}

package domain;

import static domain.Money.MAXIMUM_BET_AMOUNT;
import static domain.Money.MINIMUM_BET_AMOUNT;
import static domain.Money.MONEY_DIVIDE_UNIT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    @Test
    @DisplayName("베팅 금액이 1,000원 이상 300,000원 이하가 아닐 경우, 예외가 발생한다.")
    void moneyIsOutOfRangeTest() {
        assertThatThrownBy(() -> new Money(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.MONEY_INVALID_RANGE.getMessage(MINIMUM_BET_AMOUNT, MAXIMUM_BET_AMOUNT));
    }

    @Test
    @DisplayName("베팅 금액이 10원 단위가 아닐 경우, 예외가 발생한다.")
    void moneyUnitIsNotTenTest() {
        assertThatThrownBy(() -> new Money(10005))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.MONEY_INVALID_UNIT.getMessage(MONEY_DIVIDE_UNIT));
    }
}

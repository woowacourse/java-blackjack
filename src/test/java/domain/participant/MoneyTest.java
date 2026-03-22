package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    @Test
    @DisplayName("베팅 금액이 1,000원 이상 300,000원 이하가 아닐 경우, 예외가 발생한다.")
    void moneyIsOutOfRangeTest() {
        assertThatThrownBy(() -> new Money(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 1,000원 이상 300,000원 이하로 입력해주세요.");
    }

    @Test
    @DisplayName("베팅 금액이 10원 단위가 아닐 경우, 예외가 발생한다.")
    void moneyUnitIsNotTenTest() {
        assertThatThrownBy(() -> new Money(10005))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 10원 단위이어야 합니다.");
    }
}

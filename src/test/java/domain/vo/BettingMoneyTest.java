package domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class BettingMoneyTest {

    @DisplayName("배팅 금액이 양수가 아닌경우 예외가 발생한다.")
    @Test
    void validateNotPositive() {
        assertAll(
                () -> assertThatThrownBy(() -> new BettingMoney(-5000))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("배팅 금액은 양수로 구성되어야 합니다."),
                () -> assertThatThrownBy(() -> new BettingMoney(0))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("배팅 금액은 양수로 구성되어야 합니다.")
        );
    }
}

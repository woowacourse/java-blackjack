package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("머니가 정상적으로 생성되는지 확인")
    @Test
    void create() {
        String amount = "20000";
        Money money = new Money(amount);

        assertThat(money).isNotNull();
    }

    @DisplayName("머니의 값이 정상적으로 일치하는지 확인")
    @Test
    void equalsMoney() {
        String amount = "20000";
        Money money = new Money(amount);

        assertThat(money.getAmount()).isEqualTo(20000);
    }

    @DisplayName("머니가 1000원 단위가 아닐 경우 예외 발생")
    @Test
    void validateMoney() {
        String amount = "1500";

        assertThatThrownBy(() -> new Money(amount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 베팅 금액은 1000원 단위여야 합니다.");
    }
}

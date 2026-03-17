package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("배팅 금액이 정상적으로 생성 된다.")
    void bettingMoney_should_be_created() {
        assertThat(new Money(1000)).isInstanceOf(Money.class);
    }

    @Test
    @DisplayName("배팅 금액은 0원 이하일 수 없다.")
    void bettingMoney_should_be_positive() {
        assertThatThrownBy(() -> new Money(0)).isInstanceOf(IllegalArgumentException.class);
    }
}

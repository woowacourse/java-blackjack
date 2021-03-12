package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
    @DisplayName("같은 금액의 Money 인스턴스는 같은 객체로 간주한다.")
    @Test
    void equalsTest() {
        Money money = new Money(10000);

        assertThat(money).isEqualTo(new Money(10000));
    }
}
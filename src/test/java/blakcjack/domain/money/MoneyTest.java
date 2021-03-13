package blakcjack.domain.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
    @DisplayName("객체 생성 성공")
    @Test
    void create() {
        final Money money = new Money(10000);
        assertThat(money).isEqualTo(new Money(10000));
    }

}
package domain.batting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {
    @Test
    @DisplayName("베팅 액수는 양수이다.")
    void validValueTest() {
        Money money = new Money(1000);
        int value = money.getValue();
        assertThat(value).isEqualTo(1000);
    }

    @Test
    @DisplayName("배팅 액수가 양수가 아니면 오류를 반환한다.")
    void invalidValueTest() {
        assertThrows(IllegalArgumentException.class, () -> new Money(-1000));
    }
}

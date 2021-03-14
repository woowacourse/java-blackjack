package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @DisplayName("배팅 금액의 금액 반환 테스트")
    @Test
    void testCreateMoney() {
        Money money = Money.from("10000");

        assertThat(money.toDouble()).isEqualTo(10000);
    }

    @DisplayName("배팅 금액의 유효성 판별 테스트")
    @Test
    void testValidateMoney() {
        assertThatThrownBy(() -> Money.from("")).isInstanceOf(NumberFormatException.class);
        assertThatThrownBy(() -> Money.from("999")).isInstanceOf(IllegalArgumentException.class);
    }
}

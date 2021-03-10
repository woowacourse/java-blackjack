package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    private static final String MIN_MONEY_ERROR = "[ERROR] 배팅 금액은 0원이 될 수 없습니다.";

    @Test
    @DisplayName("수익 계산 확인")
    void moneyEarning() {
        Money money = new Money(1000);
        assertThat(money.getEarning(1.5)).isEqualTo(1500);
    }

    @Test
    @DisplayName("같은 금액이면 동일 인스턴스인지 확인")
    void moneyInstance() {
        Money money = new Money(1000);
        assertThat(money).isEqualTo(new Money(1000));
    }

    @Test
    @DisplayName("최소 배팅 금액 유효성 검사 확인")
    void validate() {
        assertThatThrownBy(() -> {
            new Money(0);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(MIN_MONEY_ERROR);
    }
}

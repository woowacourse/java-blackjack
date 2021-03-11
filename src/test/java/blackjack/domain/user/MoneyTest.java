package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MoneyTest {
    @DisplayName("Money 객체를 생성한다.")
    @Test
    public void createMoney() {
        Money money = new Money(10000);

        assertThat(money).isInstanceOf(Money.class);
    }

    @DisplayName("베팅 금액이 0원 이하인 경우 예외를 발생한다. - 0원인 경우")
    @Test
    public void validateZeroException() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Money(0);
        }).withMessage("베팅 금액은 0원 이하일 수 없습니다.");
    }

    @DisplayName("베팅 금액이 0원 이하인 경우 예외를 발생한다. - 음수인 경우")
    @Test
    public void validateNegativeException() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Money(-10000);
        }).withMessage("베팅 금액은 0원 이하일 수 없습니다.");
    }
}

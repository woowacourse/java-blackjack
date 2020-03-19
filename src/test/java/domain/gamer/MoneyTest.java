package domain.gamer;

import exception.InvalidMoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @Test
    @DisplayName("음수 값이 들어왔을 경우 테스트")
    void generateMoneyTest() {
        assertThatThrownBy(() -> new Money("-1000"))
                .isInstanceOf(InvalidMoneyException.class);
    }

    @Test
    @DisplayName("역금액 테스트")
    void reversionMoneyTest() {
        Money money = new Money("1000");
        assertThat(money.reversion().getMoney()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("0원을 반환하는 테스트")
    void getZeroMoneyTest() {
        Money money = new Money("3000");
        assertThat(money.getZeroMoney()).isEqualTo(new Money("0"));
    }

    @Test
    @DisplayName("금액 곱해서 반환하는 테스트")
    void multiplyMoneyTest() {
        Money money = new Money("1000");
        assertThat(money.multiply(1.5)).isEqualTo(new Money("1500"));
    }
}
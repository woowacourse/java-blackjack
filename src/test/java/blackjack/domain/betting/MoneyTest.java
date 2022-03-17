package blackjack.domain.betting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    @Test
    @DisplayName("돈을 배팅할 수 있다.")
    void bettingMoney() {
        Money money = new Money(10000);
        assertThat(money).isEqualTo(new Money(10000));
    }

    @Test
    @DisplayName("돈을 차감할 수 있다.")
    void decreaseMoney() {
        Money money = new Money(10000);
        money.decrease(new Money(10000));
        assertThat(money).isEqualTo(new Money(0));
    }

    @Test
    @DisplayName("돈을 추가할 수 있다.")
    void increaseMoney() {
        Money money = new Money(10000);
        money.increase(new Money(10000));
        assertThat(money).isEqualTo(new Money(20000));
    }

    @Test
    @DisplayName("돈은 음수일 수 없다.")
    void throwWhenNegative() {
        assertThatThrownBy(() -> new Money(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 금액은 음수일 수 없습니다.");
    }

}

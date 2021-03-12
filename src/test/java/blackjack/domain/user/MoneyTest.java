package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @Test
    @DisplayName("돈을 나타나는 객체를 생성한다.")
    void create() {
        Money money = new Money(10000);

        assertThat(money).isInstanceOf(Money.class);
    }

    @Test
    @DisplayName("0보다 작은 금액 입력시 예외가 발생한다.")
    void creatException() {
        assertThatThrownBy(() -> {
            Money money = new Money(-3);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}

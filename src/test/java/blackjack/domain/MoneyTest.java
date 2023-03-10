package blackjack.domain;

import static blackjack.domain.Money.SUBTRACT_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    private Money money;

    @BeforeEach
    void setUp() {
        money = new Money(0);
    }

    @Test
    @DisplayName("돈을 더 받을 수 있다.")
    void addTest() {
        Money newMoney = money.add(new Money(10000));

        assertThat(newMoney.getMoney()).isEqualTo(10000);
    }

    @Test
    @DisplayName("현재 금액보다 큰 수를 빼면 예외가 발생한다.")
    void subtractTest_exception() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> money.subtract(new Money(10000))
        ).withMessage(SUBTRACT_ERROR_MESSAGE);
    }

    @Test
    void subtractTest() {
        money = new Money(10000);

        Money newMoney = money.subtract(new Money(10000));

        assertThat(newMoney.getMoney()).isEqualTo(0);
    }
}

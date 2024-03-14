package blackjack.model.betting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("금액 입력 시 0 이하의 숫자인 경우 예외를 발생한다.")
    void createBettingAmountByNotPositiveNumber() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Money(-1))
                .withMessage("금액은 양수만 가능합니다.");
    }

    @Test
    @DisplayName("돈을 받는다.")
    void add() {
        Money money = new Money(1_000);
        assertThat(money.add(1_000)).isEqualTo(new Money(2_000));
    }

    @Test
    @DisplayName("돈을 잃는다.")
    void subtract() {
        Money money = new Money(1_000);
        assertThat(money.subtract(1_000)).isEqualTo(new Money(0));
    }
}

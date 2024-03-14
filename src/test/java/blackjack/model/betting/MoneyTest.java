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
                .withMessage("배팅 금액은 양수만 가능합니다.");
    }

    @Test
    @DisplayName("배팅 금액의 곱을 구한다.")
    void multiple() {
        Money money = new Money(1_000);
        assertThat(money.multiple(1.5)).isEqualTo(new Money(1_500));
    }

    @Test
    @DisplayName("배팅 금액의 전체를 잃는다.")
    void payAll() {
        Money money = new Money(1_000);
        assertThat(money.payAll()).isEqualTo(new Money(0));
    }
}

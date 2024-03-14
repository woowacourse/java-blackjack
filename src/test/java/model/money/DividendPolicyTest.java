package model.money;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DividendPolicyTest {

    @Test
    @DisplayName("INIT_BLACKJACK일 경우는 1.5배 곱한 결과의 돈이 생성된다.")
    void apply_ShouldReturnNewInstanceOfMultiplyOf1point5_WhenInitBlackJack() {
        Money money = new Money(1000);

        Money expected = DividendPolicy.INIT_BLACKJACK.apply(money);
        Money actual = new Money(1500);

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    @DisplayName("NORMAL_WIN일 경우는 1배 곱한 결과의 돈이 생성된다.")
    void apply_ShouldReturnNewInstanceOfMultiplyOf1_WhenNormalWin() {
        Money money = new Money(1000);

        Money expected = DividendPolicy.NORMAL_WIN.apply(money);
        Money actual = new Money(1000);

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    @DisplayName("NORMAL_DRAW일 경우는 0을 곱한 결과의 돈이 생성된다.")
    void apply_ShouldReturnNewInstanceOfMultiplyOf0_WhenNormalDraw() {
        Money money = new Money(1000);

        Money expected = DividendPolicy.NORMAL_DRAW.apply(money);
        Money actual = new Money(1000).createMultiplyOf(0);

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    @DisplayName("NORMAL_LOSE일 경우는 -1을 곱한 결과의 돈이 생성된다.")
    void apply_ShouldReturnNewInstanceOfMultiplyOfNegative1_WhenNormalLose() {
        Money money = new Money(1000);

        Money expected = DividendPolicy.NORMAL_LOSE.apply(money);
        Money actual = new Money(1000).createMultiplyOf(-1);

        assertThat(expected).isEqualTo(actual);
    }
}

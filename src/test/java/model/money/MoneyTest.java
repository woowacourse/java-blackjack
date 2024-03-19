package model.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("생성자를 통해 생성되는 돈은 일때 예외를 발생시킨다.")
    void createBettingAmount_ShouldThrowException_WhenMoneyAmountIsNotInteger() {
        assertThatThrownBy(() -> Money.createBettingAmount("-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("돈은 N(실수)을 곱한 만큼의 돈을 가지는 새로운 인스턴스를 생성한다.")
    void createMultiplyOf_ShouldGenerateInstanceOfMultiplyNOfOrigin_WhenNIsDouble() {
        assertThat(new Money(1000).createMultiplyOf(1.5))
                .isEqualTo(new Money(1500));
    }

    @Test
    @DisplayName("돈은 N(정수)을 곱한 만큼의 돈을 가지는 새로운 인스턴스를 생성한다.")
    void createMultiplyOf_ShouldGenerateInstanceOfMultiplyNOfOrigin_WhenNIsInteger() {
        assertThat(new Money(1000).createMultiplyOf(2))
                .isEqualTo(new Money(2000));
    }

    @Test
    @DisplayName("두개의 돈의 값을 서로 합하여 새로운 돈울 반환한다.")
    void increase_ShouldGenerateSumAmountOfMoney_WhenArgumentMoneyIsPositive() {
        assertThat(new Money(1000).increase(new Money(400)))
                .isEqualTo(new Money(1400));
    }

    @Test
    @DisplayName("두개의 돈의 값을 서로 빼주어 새로운 돈을 반환한다.")
    void decrease_ShouldGenerateSubtractionOfMoney_WhenArgumentMoneyIsPositive() {
        assertThat(new Money(1000).decrease(new Money(400)))
                .isEqualTo(new Money(600));
    }
}

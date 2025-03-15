package participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("돈을 더해서 반환한다.")
    void test1() {
        // given
        Money money = Money.of(10000);

        // when
        Money addedMoney = money.add(10000);

        // then
        assertThat(addedMoney.getAmount())
                .isEqualTo(20000);
    }

    @Test
    @DisplayName("돈을 빼서 반환한다.")
    void test2() {
        // given
        Money money = Money.of(10000);

        // when
        Money addedMoney = money.minus(10000);

        // then
        assertThat(addedMoney.getAmount())
                .isEqualTo(0);
    }

    @Test
    @DisplayName("돈을 곱해서 반환한다.")
    void test3() {
        // given
        Money money = Money.of(10000);

        // when
        Money addedMoney = money.multiply(3);

        // then
        assertThat(addedMoney.getAmount())
                .isEqualTo(30000);
    }
}

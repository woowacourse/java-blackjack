package model.money;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("돈이 추가가 된다.")
    void whenAddMoney_thenSuccess() {
        // given
        final Money money = new Money(10_000);
        final Money addMoney = new Money(10_000);

        // when
        final Money result = money.add(addMoney);

        // then
        assertThat(result).isEqualTo(new Money(20_000));
    }
    @Test
    @DisplayName("게임에서 질 경우 돈의 값이 마이너스로 바뀐다.")
    void whenLoseGame_thenReturnLoseMoney() {
        // given
        final Money money = new Money(10_000);

        // when
        final Money loseMoney = money.lose();

        // then
        assertThat(loseMoney).isEqualTo(new Money(-10_000));
    }

    @Test
    @DisplayName("게임에서 블랙잭으로 이길 경우 돈의 값이 1.5배로 바뀐다.")
    void whenBlackJack_thenReturnBlackJackMoney() {
        // given
        final Money money = new Money(10_000);

        // when
        final Money blackJack = money.blackJack();

        // then
        assertThat(blackJack).isEqualTo(new Money(15_000));
    }
}

package model.money;

import static model.user.Result.LOSE;
import static model.user.Result.TIE;
import static model.user.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
    @DisplayName("게임에서 블랙잭으로 이길 경우 돈의 값이 1.5배로 바뀐다.")
    void whenBlackJack_thenReturnBlackJackMoney() {
        // given
        final Money money = new Money(10_000);

        // when
        final Money blackJack = money.blackJack();

        // then
        assertThat(blackJack).isEqualTo(new Money(15_000));
    }

    @Nested
    @DisplayName("Result에 따라 돈을 계산한다")
    class calculateByResult {

        @Test
        @DisplayName("게임에서 질 경우 돈의 값이 마이너스로 바뀐다.")
        void whenLoseGame_thenReturnLoseMoney() {
            // given
            final Money money = new Money(10_000);

            // when
            final Money lose = money.calculateMoney(LOSE);

            // then
            assertThat(lose).isEqualTo(new Money(-10_000));
        }

        @Test
        @DisplayName("게임에서 비길 경우 돈을 돌려 받는다.")
        void whenTie_thenReturnMoney() {
            // given
            final Money money = new Money(10_000);

            // when
            final Money tie = money.calculateMoney(TIE);

            // then
            assertThat(tie).isEqualTo(new Money(0));
        }

        @Test
        @DisplayName("게임에서 이길 경우 돈을 돌려 받는다.")
        void whenWin_thenReturnMoney() {
            // given
            final Money money = new Money(10_000);

            // when
            final Money tie = money.calculateMoney(WIN);

            // then
            assertThat(tie).isEqualTo(new Money(10_000));
        }
    }
}

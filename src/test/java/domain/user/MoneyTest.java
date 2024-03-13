package domain.user;

import static domain.game.GameResult.DRAW;
import static domain.game.GameResult.LOSE;
import static domain.game.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import domain.game.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    @Test
    @DisplayName("승리 시, 베팅 금액만큼 금액을 얻는다.")
    void betAmountTimesTest() {
        Money money = new Money(10000);

        Money newAmount = money.change(WIN);

        assertThat(newAmount.value()).isEqualTo(10000);
    }

    @Test
    @DisplayName("무승부 시, 0원이 된다.")
    void betAmountDrawTest() {
        Money money = new Money(10000);

        Money newAmount = money.change(DRAW);

        assertThat(newAmount.value()).isEqualTo(0);
    }

    @Test
    @DisplayName("패배 시, 베팅 금액을 잃는다.")
    void betAmountLoseTest() {
        Money money = new Money(10000);

        Money newAmount = money.change(LOSE);

        assertThat(newAmount.value()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("블랙잭 배수만큼 곱한다.")
    void changeByBlackJackTest() {
        Money money = new Money(10000);

        Money newAmount = money.changeByBlackJack();

        assertThat(newAmount.value()).isEqualTo(15000);
    }
}

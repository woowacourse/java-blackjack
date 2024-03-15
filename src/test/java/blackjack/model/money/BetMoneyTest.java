package blackjack.model.money;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetMoneyTest {

    @Test
    @DisplayName("전달 받은 금액으로 Money를 생성한다.")
    void createMoney() {
        int money = 1_000;
        assertThat(new BetMoney(money).getBetMoney()).isEqualTo(1_000);
    }

    @Test
    @DisplayName("현재 금액에 전달 받은 숫자를 곱한다.")
    void multiply() {
        BetMoney betMoney = new BetMoney(1_000);
        int newBetMoney = betMoney.multiply(1.5);

        assertThat(newBetMoney).isEqualTo(1_500);
    }
}

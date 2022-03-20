package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingMoneyTest {

    @Test
    @DisplayName("베팅 금액이 음수인 경우, 예외를 발생한다.")
    void bettingMoneyException() {
        assertThatThrownBy(() -> new BettingMoney(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 0보다 큰 양수로 입력해주세요.");
    }

    @Test
    @DisplayName("베팅 금액이 정상 입력된 경우, BettingMoney 가 정상적으로 생성된다.")
    void createBettingMoney() {
        final int expectedMoney = 1000;
        final BettingMoney bettingMoney = new BettingMoney(expectedMoney);

        final int actual = bettingMoney.getValue();

        assertThat(actual).isEqualTo(expectedMoney);
    }
}

package blackjack.domain.betting;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingMoneyTest {


    @Test
    @DisplayName("참가자로 부터 배팅금액을 입력받는다.")
    void createBettingMoney() {
        BettingMoney bettingMoney = new BettingMoney(1000);

        assertThat(bettingMoney.getBettingMoney()).isEqualTo(1000);
    }

    @Test
    @DisplayName("배팅금액이 100미만이면 예외가 발생한다.")
    void createBettingMoneyNegativeFail1() {
        assertThatThrownBy(() -> new BettingMoney(99))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 배팅 금액에는 100미만의 값은 올 수 없습니다.");
    }

    @Test
    @DisplayName("배팅금액이 100미만이면 예외가 발생한다.")
    void createBettingMoneyNegativeFail2() {
        assertThatThrownBy(() -> new BettingMoney(-1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 배팅 금액에는 100미만의 값은 올 수 없습니다.");
    }

    @Test
    @DisplayName("배팅금액이 100으로 나누어떨어지지 않으면 예외가 발생한다.")
    void createBettingMoneyNegativeFail() {
        assertThatThrownBy(() -> new BettingMoney(1111))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 배팅 금액은 100원 단위여야 합니다.");
    }

}

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
    @DisplayName("배팅금액이 0이하이면 예외가 발생한다.")
    void createBettingMoneyNegativeFail() {
        assertThatThrownBy(() -> new BettingMoney(-1000))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 배팅 금액에는 0이하의 값은 올 수 없습니다.");
    }



}

package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingAmountTest {

    @Test
    @DisplayName("초기 배팅 금액은 10000원 이상이 아니라면 예외가 발생한다.")
    void createBettingAmountFail() {
        int money = 9999;

        assertThatThrownBy(() -> BettingAmount.fromPlayer(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("초기 배팅 금액은 10000원 이상이어야 합니다.");
    }

    @Test
    @DisplayName("초기 배팅 금액이 10000원 이상이라면 배팅 금액이 생성된다.")
    void createBettingAmountSuccess() {
        int money = 10000;

        BettingAmount bettingAmount = BettingAmount.fromPlayer(money);

        assertThat(bettingAmount.getRevenue()).isEqualTo(money);
    }

}

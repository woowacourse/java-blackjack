package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetMoneyTest {

    @Test
    @DisplayName("베팅 금액을 저장하는 클래스를 생성한다.")
    void create_bet_money() {
        int money = 10000;
        BetMoney betMoney = new BetMoney(money);

        assertThat(betMoney.get()).isEqualTo(money);
    }

    @Test
    @DisplayName("베팅 금액은 1원 이상 입력해야한다.")
    void under_min_amount_error() {
        int money = 0;

        assertThatThrownBy(() -> new BetMoney(money))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

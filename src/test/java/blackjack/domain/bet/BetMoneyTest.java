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
    @DisplayName("수익률을 파라미터로 받아 베팅 금액의 수익 금액을 반환한다.")
    void get_profit() {
        int money = 10000;
        BetMoney betMoney = new BetMoney(money);
        double rate = 1.5;

        Profit profit = betMoney.getProfit(rate);

        assertThat(profit.get()).isEqualTo((int) (money * rate));
    }

    @Test
    @DisplayName("베팅 금액은 10원 단위로 입력해야한다.")
    void amount_error() {
        int money = 1;

        assertThatThrownBy(() -> new BetMoney(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("베팅 금액은 양수로 입력해야한다.")
    void negative_zero_number_error() {
        int money = 0;

        assertThatThrownBy(() -> new BetMoney(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("equals, hashCode, toString 테스트")
    void equals() {
        int input = 10000;
        BetMoney o1 = new BetMoney(input);
        BetMoney o2 = new BetMoney(input);
        Object o = new Object();

        assertThat(o1.equals(o2)).isTrue();
        assertThat(o1.equals(o1)).isTrue();
        assertThat(o1.equals(o)).isFalse();
        assertThat(o1.hashCode() == o2.hashCode()).isTrue();
        assertThat(o1.toString()).isEqualTo(o2.toString());
    }
}

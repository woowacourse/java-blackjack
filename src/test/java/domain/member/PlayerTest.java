package domain.member;

import domain.state.Blackjack;
import domain.state.Bust;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @DisplayName("블랙잭 상태일 때 베팅 금액의 1.5배 수익을 반환한다")
    @Test
    void calculateProfit_StateIsBlackjack_ReturnAccurateEarning() {
        Money betMoney = new Money(10000);
        Player player = new Player("pobi", betMoney, new Blackjack(new Hand()));
        int expected = 15000;

        int profit = player.calculateProfit();

        assertThat(profit).isEqualTo(expected);
    }

    @DisplayName("버스트 상태일 때 베팅 금액을 모두 잃는다 (손해 금액 반환)")
    @Test
    void calculateProfit_StateIsBust_ReturnLostEarning() {
        Money betMoney = new Money(10000);
        Player player = new Player("pobi", betMoney, new Bust(new Hand()));
        int expected = -10000;

        int profit = player.calculateProfit();

        assertThat(profit).isEqualTo(expected);
    }
}

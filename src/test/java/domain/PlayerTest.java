package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("딜러 카드들과 비교하여 수익을 계산하여 반환한다")
    @Test
    void test() {
        // given
        Hand dealerHand = new Hand(List.of(
                new Card(CardNumberType.EIGHT, CardType.CLOVER),
                new Card(CardNumberType.FIVE, CardType.HEART)
        ));
        Hand playerHand = new Hand(List.of(
                new Card(CardNumberType.EIGHT, CardType.CLOVER),
                new Card(CardNumberType.EIGHT, CardType.HEART)
        ));
        BettingMoney bettingMoney = new BettingMoney(10000);
        Player player = new Player("웨이드", playerHand, bettingMoney);

        // when
        Profit profit = player.calculateProfit(dealerHand);

        // then
        assertThat(profit.toIntValue()).isEqualTo(bettingMoney.bettingMoney());
    }
}

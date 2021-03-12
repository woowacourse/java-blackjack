package blackjack.domain.blackjackgame;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("돈을 추가한다.")
    @Test
    void testMoneyAdd() {
        Money money = new Money(1000);

        assertThat(money.sum(new Money(500)).getValue()).isEqualTo(1500);
    }

    @DisplayName("수익 비율만큼의 Money가 리턴된다.")
    @Test
    void testBlackjackWinProfit() {
        Money money = new Money(1000);
        assertThat(money.profit(1.5).getValue()).isEqualTo(1500);
    }

}
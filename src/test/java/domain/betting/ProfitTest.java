package domain.betting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {
    @Test
    @DisplayName("이겼을 때 정상적으로 돈을 주는지 확인한다.")
    void winProfitTest() {
        // Given
        Money money = new Money(1000);
        Profit profit = new Profit("플레이어1", money, BettingResult.WIN);

        // When
        long bettingResultMoney = profit.calculateProfit();

        // Then
        Assertions.assertThat(bettingResultMoney).isEqualTo(1000);
    }

    @Test
    @DisplayName("비겼을 때 돈을 다시 돌려받는지 확인한다.")
    void drawProfitTest() {
        // Given
        Money money = new Money(3000);
        Profit profit = new Profit("플레이어1", money, BettingResult.DRAW);

        // When
        long bettingResultMoney = profit.calculateProfit();

        // Then
        Assertions.assertThat(bettingResultMoney).isEqualTo(0);
    }


    @Test
    @DisplayName("졌을 때 손해인지 확인한다.")
    void loseProfitTest() {
        // Given
        Money money = new Money(3000);
        Profit profit = new Profit("플레이어1", money, BettingResult.LOSE);

        // When
        long bettingResultMoney = profit.calculateProfit();

        // Then
        Assertions.assertThat(bettingResultMoney).isEqualTo(-3000);
    }

    @Test
    @DisplayName("블랙잭으로 이겼을 때 1.5배로 받는지 확인한다.")
    void blackJackWinProfitTest() {
        // Given
        Money money = new Money(3000);
        Profit profit = new Profit("플레이어1", money, BettingResult.WIN_WITH_BLACKJACK);

        // When
        long bettingResultMoney = profit.calculateProfit();

        // Then
        Assertions.assertThat(bettingResultMoney).isEqualTo(4500);
    }

    @Test
    @DisplayName("소숫점은 반올림연산으로 처리한다.")
    void floatProfitTest() {
        // Given
        Money money = new Money(1);
        Profit profit = new Profit("플레이어1", money, BettingResult.WIN_WITH_BLACKJACK);

        // When
        long bettingResultMoney = profit.calculateProfit();

        // Then
        Assertions.assertThat(bettingResultMoney).isEqualTo(2);
    }
}

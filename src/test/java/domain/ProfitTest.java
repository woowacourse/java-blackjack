package domain;

import domain.game.ResultStatus;
import domain.money.Bet;
import domain.money.Profit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {
    
    
    @Test
    @DisplayName("수익 정적 팩토리 메서드 테스트")
    void createFromBet() {
        Bet bet = new Bet(1000);
        Profit profit = Profit.from(bet);
        Assertions.assertThat(profit.getProfit()).isEqualTo(1000);
    }
    
    
    @Test
    @DisplayName("수익 계산 테스트")
    void calculateProfitFromResult() {
        Bet bet = new Bet(1000);
        Profit profit = Profit.from(bet);
        Profit calculatedProfit = profit.calculateProfitFromBetAndResult(ResultStatus.WIN);
        Assertions.assertThat(calculatedProfit.getProfit()).isEqualTo(1000);
        Profit calculatedProfit2 = profit.calculateProfitFromBetAndResult(ResultStatus.WIN_BLACKJACK);
        Assertions.assertThat(calculatedProfit2.getProfit()).isEqualTo(1500);
        Profit calculatedProfit3 = profit.calculateProfitFromBetAndResult(ResultStatus.DRAW);
        Assertions.assertThat(calculatedProfit3.getProfit()).isEqualTo(0);
        Profit calculatedProfit4 = profit.calculateProfitFromBetAndResult(ResultStatus.LOSE);
        Assertions.assertThat(calculatedProfit4.getProfit()).isEqualTo(-1000);
    }
}
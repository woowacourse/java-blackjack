package domain;

import domain.game.ResultStatus;
import domain.money.Bet;
import domain.money.Profit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {
    
    
    @Test
    @DisplayName("수익 생성 테스트")
    void calculateProfitFromResult() {
        Bet bet = new Bet(1000);
        Profit calculatedProfit = Profit.create(bet, ResultStatus.WIN);
        Assertions.assertThat(calculatedProfit.getProfit()).isEqualTo(1000);
        Profit calculatedProfit2 = Profit.create(bet, ResultStatus.WIN_BLACKJACK);
        Assertions.assertThat(calculatedProfit2.getProfit()).isEqualTo(1500);
        Profit calculatedProfit3 = Profit.create(bet, ResultStatus.DRAW);
        Assertions.assertThat(calculatedProfit3.getProfit()).isEqualTo(0);
        Profit calculatedProfit4 = Profit.create(bet, ResultStatus.LOSE);
        Assertions.assertThat(calculatedProfit4.getProfit()).isEqualTo(-1000);
    }
    
    @Test
    @DisplayName("수익 합산 테스트")
    void addProfit() {
        Bet bet = new Bet(1000);
        Profit calculatedProfit = Profit.create(bet, ResultStatus.WIN);
        Profit calculatedProfit2 = Profit.create(bet, ResultStatus.WIN);
        Assertions.assertThat(calculatedProfit.add(calculatedProfit2).getProfit()).isEqualTo(2000);
    }
}
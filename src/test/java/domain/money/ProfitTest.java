package domain.money;

import domain.game.GameStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {
    
    
    @Test
    @DisplayName("수익 생성 테스트")
    void calculateProfitFromResult() {
        Bet bet = new Bet(1000);
        Profit calculatedProfit = Profit.create(bet, GameStatus.WIN);
        Assertions.assertThat(calculatedProfit.getProfit()).isEqualTo(1000);
        Profit calculatedProfit2 = Profit.create(bet, GameStatus.WIN_BLACKJACK);
        Assertions.assertThat(calculatedProfit2.getProfit()).isEqualTo(1500);
        Profit calculatedProfit3 = Profit.create(bet, GameStatus.DRAW);
        Assertions.assertThat(calculatedProfit3.getProfit()).isEqualTo(0);
        Profit calculatedProfit4 = Profit.create(bet, GameStatus.LOSE);
        Assertions.assertThat(calculatedProfit4.getProfit()).isEqualTo(-1000);
    }
    
    @Test
    @DisplayName("수익 합산 테스트")
    void addProfit() {
        Bet bet = new Bet(1000);
        Profit calculatedProfit = Profit.create(bet, GameStatus.WIN);
        Profit calculatedProfit2 = Profit.create(bet, GameStatus.WIN);
        Assertions.assertThat(calculatedProfit.add(calculatedProfit2).getProfit()).isEqualTo(2000);
    }
}
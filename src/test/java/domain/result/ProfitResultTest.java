package domain.result;

import domain.game.GameStatus;
import domain.money.Bet;
import domain.user.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitResultTest {
    
    
    @Test
    @DisplayName("플레이어들의 수익 결과 테스트")
    void getProfitMapTest() {
        Player echo = new Player("echo");
        Player split = new Player("split");
        Player pobi = new Player("pobi");
        Player crong = new Player("crong");
        echo.addBet(new Bet(1000));
        split.addBet(new Bet(1000));
        pobi.addBet(new Bet(1000));
        crong.addBet(new Bet(1000));
        ProfitResult profitResult = new ProfitResult();
        profitResult.accumulate(echo, echo.getBet(), GameStatus.WIN);
        profitResult.accumulate(split, split.getBet(), GameStatus.DRAW);
        profitResult.accumulate(pobi, pobi.getBet(), GameStatus.WIN_BLACKJACK);
        profitResult.accumulate(crong, crong.getBet(), GameStatus.LOSE);
        Assertions.assertThat(profitResult.getProfitMap().get(echo).getProfit()).isEqualTo(1000);
        Assertions.assertThat(profitResult.getProfitMap().get(split).getProfit()).isEqualTo(0);
        Assertions.assertThat(profitResult.getProfitMap().get(pobi).getProfit()).isEqualTo(1500);
        Assertions.assertThat(profitResult.getProfitMap().get(crong).getProfit()).isEqualTo(-1000);
    }
}
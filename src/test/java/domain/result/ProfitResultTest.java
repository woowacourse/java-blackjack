package domain.result;

import domain.game.GameBet;
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
        StatusResult statusResult = new StatusResult();
        GameBet gameBet = new GameBet();
        Player echo = new Player("echo");
        Player split = new Player("split");
        Player pobi = new Player("pobi");
        Player crong = new Player("crong");
        statusResult.accumulate(echo, GameStatus.WIN);
        statusResult.accumulate(split, GameStatus.DRAW);
        statusResult.accumulate(pobi, GameStatus.WIN_BLACKJACK);
        statusResult.accumulate(crong, GameStatus.LOSE);
        gameBet.accumulate(echo, new Bet(1000));
        gameBet.accumulate(split, new Bet(1000));
        gameBet.accumulate(pobi, new Bet(1000));
        gameBet.accumulate(crong, new Bet(1000));
        ProfitResult profitResult = ProfitResult.create(gameBet, statusResult);
        Assertions.assertThat(profitResult.getProfitMap().get(echo).getProfit()).isEqualTo(1000);
        Assertions.assertThat(profitResult.getProfitMap().get(split).getProfit()).isEqualTo(0);
        Assertions.assertThat(profitResult.getProfitMap().get(pobi).getProfit()).isEqualTo(1500);
        Assertions.assertThat(profitResult.getProfitMap().get(crong).getProfit()).isEqualTo(-1000);
    }
}
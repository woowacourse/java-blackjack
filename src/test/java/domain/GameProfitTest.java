package domain;

import domain.game.GameBet;
import domain.game.GameProfit;
import domain.game.GameResult;
import domain.game.ResultStatus;
import domain.money.Bet;
import domain.user.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameProfitTest {
    
    
    @Test
    @DisplayName("플레이어들의 수익 결과 테스트")
    void getProfitMapTest() {
        GameResult gameResult = new GameResult();
        GameBet gameBet = new GameBet();
        Player echo = new Player("echo");
        Player split = new Player("split");
        Player pobi = new Player("pobi");
        Player crong = new Player("crong");
        gameResult.accumulate(echo, ResultStatus.WIN);
        gameResult.accumulate(split, ResultStatus.DRAW);
        gameResult.accumulate(pobi, ResultStatus.WIN_BLACKJACK);
        gameResult.accumulate(crong, ResultStatus.LOSE);
        gameBet.accumulate(echo, new Bet(1000));
        gameBet.accumulate(split, new Bet(1000));
        gameBet.accumulate(pobi, new Bet(1000));
        gameBet.accumulate(crong, new Bet(1000));
        GameProfit gameProfit = GameProfit.create(gameBet, gameResult);
        Assertions.assertThat(gameProfit.getProfitMap().get(echo).getProfit()).isEqualTo(1000);
        Assertions.assertThat(gameProfit.getProfitMap().get(split).getProfit()).isEqualTo(0);
        Assertions.assertThat(gameProfit.getProfitMap().get(pobi).getProfit()).isEqualTo(1500);
        Assertions.assertThat(gameProfit.getProfitMap().get(crong).getProfit()).isEqualTo(-1000);
    }
}
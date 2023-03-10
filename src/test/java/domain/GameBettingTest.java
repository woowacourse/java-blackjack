package domain;

import domain.game.GameBetting;
import domain.money.Bet;
import domain.user.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameBettingTest {
    
    @Test
    @DisplayName("플레이어들의 배팅금 추가 테스트")
    void addBettingMoneyTest() {
        GameBetting gameBetting = new GameBetting();
        Player echo = new Player("echo");
        gameBetting.accumulate(echo, new Bet(1000));
        Player split = new Player("split");
        gameBetting.accumulate(split, new Bet(2000));
        Assertions.assertThat(gameBetting.getBetMap().get(echo)).isEqualTo(new Bet(1000));
        Assertions.assertThat(gameBetting.getBetMap().get(split)).isEqualTo(new Bet(2000));
    }
}
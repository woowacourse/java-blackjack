package domain;

import domain.game.GameBet;
import domain.money.Bet;
import domain.user.Player;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameBetTest {
    
    @Test
    @DisplayName("플레이어들의 배팅금 추가 테스트")
    void addBettingMoneyTest() {
        GameBet gameBet = new GameBet();
        Player echo = new Player("echo");
        Player split = new Player("split");
        gameBet.accumulate(echo, new Bet(1000));
        gameBet.accumulate(split, new Bet(2000));
        Assertions.assertThat(gameBet).extracting("betMap").asInstanceOf(InstanceOfAssertFactories.MAP)
                .containsEntry(echo, new Bet(1000));
    }
    
}
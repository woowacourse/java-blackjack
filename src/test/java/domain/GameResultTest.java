package domain;

import domain.participants.Dealer;
import domain.participants.Player;
import domain.participants.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class  GameResultTest {
    @Test
    @DisplayName("GameResult 생성 성공 테스트")
    void createGameResultTest(){
        Player player1 = new Player("pobi",new BettingMoney(0));
        Player player2 = new Player("jason",new BettingMoney(0));
        Dealer dealer = new Dealer();
        Assertions.assertThatNoException().isThrownBy(()->new GameResult(new Players(List.of(dealer,player1, player2))));
    }

    @Test
    @DisplayName("GameResult 베팅 금액 추가 후 조회 테스트 ")
    void addGameResultTest(){
        Player player1 = new Player("pobi",new BettingMoney(0));
        Player player2 = new Player("jason",new BettingMoney(0));
        Dealer dealer = new Dealer();

        Players players = new Players(List.of(dealer,player1,player2));
        GameResult gameResult = new GameResult(players);
        gameResult.addBetMoney(players.getPlayersWithOutDealer().get(0),new BettingMoney(10000));

        Assertions.assertThat(gameResult.getPlayerProfit(players.getPlayersWithOutDealer().get(0))).isEqualTo(10000);
    }

    @Test
    @DisplayName("GameResult 베팅 금액 계산 테스트 ")
    void calculateGameResultTest(){
        Player player1 = new Player("pobi",new BettingMoney(0));
        Player player2 = new Player("jason",new BettingMoney(0));
        Dealer dealer = new Dealer();

        Players players = new Players(List.of(dealer,player1,player2));
        GameResult gameResult = new GameResult(players);
        gameResult.calculatePlayersResult(players);

        Player player = players.getPlayersWithOutDealer().get(0);
        gameResult.addBetMoney(player,new BettingMoney(10000));

        Assertions.assertThat(gameResult.getPlayerProfit(player)).isEqualTo(10000);
    }
}

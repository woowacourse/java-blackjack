package domain;

import domain.deck.Card;
import domain.deck.CardNumber;
import domain.deck.CardPattern;
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
        Assertions.assertThatNoException().isThrownBy(()->new GameResult());
    }

    @Test
    @DisplayName("GameResult 베팅 금액 추가 후 조회 테스트 ")
    void addGameResultTest(){
        Player player1 = new Player("pobi",new BettingMoney(10000));
        player1.addCard(new Card(CardNumber.ACE, CardPattern.SPADE));
        Dealer dealer = new Dealer();

        Players players = new Players(List.of(player1));
        GameResult gameResult = new GameResult();
        gameResult.calculatePlayersResult(dealer,players);

        Assertions.assertThat(gameResult.calculateResult(dealer,player1)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("GameResult 베팅 금액 계산 테스트 ")
    void calculateGameResultTest(){
        Player player1 = new Player("pobi",new BettingMoney(10000));
        player1.addCard(new Card(CardNumber.ACE, CardPattern.SPADE));
        player1.addCard(new Card(CardNumber.TEN, CardPattern.SPADE));

        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.TEN, CardPattern.DIAMOND));
        Players players = new Players(List.of(player1));
        GameResult gameResult = new GameResult();
        gameResult.calculatePlayersResult(dealer,players);
        
        Assertions.assertThat(gameResult.calculateResult(dealer,player1)).isEqualTo(Result.BLACKJACKWIN);
    }
}

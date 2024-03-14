package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Rank;
import blackjack.domain.cards.Shape;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.GamblingMoney;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import blackjack.domain.participants.Victory;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameBoardTest {
    private Player siso;
    private Player takan;
    private Players players;
    private GameBoard gameBoard;

    @BeforeEach
    void beforeEach() {
        siso = new Player(new Name("시소"));
        takan = new Player(new Name("타칸"));

        siso.receiveCard(new Card(Shape.HEART, Rank.JACK));
        siso.receiveCard(new Card(Shape.HEART, Rank.SIX)); // 16

        takan.receiveCard(new Card(Shape.SPADE, Rank.ACE));
        takan.receiveCard(new Card(Shape.SPADE, Rank.JACK)); // 21

        List<Player> playerList = List.of(siso, takan);
        players = new Players(playerList);

        gameBoard = new GameBoard(players);

    }

    @Test
    @DisplayName("플레이어의 수를 센다.")
    void countPlayerTest() {
        int result = gameBoard.countPlayers();

        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어가 카드를 더 가질 수 있다.")
    void isPlayerNotOverTest() {
        boolean result = gameBoard.isPlayerNotOver(0);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("딜러가 카드를 더 가질 수 있다.")
    void isDealerNotOverTest() {
        Dealer dealer = gameBoard.getDealer();
        dealer.receiveCard(new Card(Shape.HEART, Rank.SEVEN));
        dealer.receiveCard(new Card(Shape.HEART, Rank.SIX));

        boolean result = gameBoard.isDealerNotOver();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("초기에 딜러와 플레이어는 카드 두 장을 받는다.")
    void initialDistributeTest() {
        Dealer dealer = gameBoard.getDealer();
        gameBoard.distributeInitialHand();

        assertThat(dealer.getHand().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어가 카드 한 장을 더 받는다.")
    void addCardToPlayerTest() {
        gameBoard.addCardToPlayer(0);

        assertThat(siso.getHand().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러가 카드 한 장을 더 받는다.")
    void addCardToDealerTest() {
        Dealer dealer = gameBoard.getDealer();
        gameBoard.addCardToDealer();

        assertThat(dealer.getHand().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어의 무승부가 포함된 테스트")
    void victoryLoseTest() {
        Dealer dealer = gameBoard.getDealer();
        dealer.receiveCard(new Card(Shape.HEART, Rank.ACE));
        dealer.receiveCard(new Card(Shape.HEART, Rank.JACK));

        Map<Player, Victory> victoryResult = gameBoard.calculateVictory();

        assertThat(victoryResult.get(siso)).isEqualTo(Victory.LOSE);
        assertThat(victoryResult.get(takan)).isEqualTo(Victory.TIE);
    }

    @Test
    @DisplayName("플레이어의 승리가 포함된 테스트")
    void victoryWinTest() {
        Dealer dealer = gameBoard.getDealer();
        dealer.receiveCard(new Card(Shape.HEART, Rank.QUEEN));
        dealer.receiveCard(new Card(Shape.HEART, Rank.JACK));

        Map<Player, Victory> victoryResult = gameBoard.calculateVictory();

        assertThat(victoryResult.get(takan)).isEqualTo(Victory.BLACKJACK_WIN);
        assertThat(victoryResult.get(siso)).isEqualTo(Victory.LOSE);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이겼을 때 배팅 이익을 잘 계산한다.")
    void calculateBettingMoneyWhenBlackjackWinTest() {
        takan.betMoney(new GamblingMoney(1000));
        Dealer dealer = gameBoard.getDealer();
        dealer.receiveCard(new Card(Shape.HEART, Rank.QUEEN));
        dealer.receiveCard(new Card(Shape.HEART, Rank.EIGHT));

        gameBoard.calculateBettingMoney(gameBoard.calculateVictory());

        assertThat(takan.getMoney().getValue()).isEqualTo(1500);
        assertThat(dealer.getMoney().getValue()).isEqualTo(-1500);
    }

    @Test
    @DisplayName("플레이어가 이겼을 때 배팅 이익을 잘 계산한다.")
    void calculateBettingMoneyWhenWinTest() {
        siso.betMoney(new GamblingMoney(1000));
        Dealer dealer = gameBoard.getDealer();
        dealer.receiveCard(new Card(Shape.HEART, Rank.QUEEN));
        dealer.receiveCard(new Card(Shape.HEART, Rank.FIVE));

        gameBoard.calculateBettingMoney(gameBoard.calculateVictory());

        assertThat(siso.getMoney().getValue()).isEqualTo(1000);
        assertThat(dealer.getMoney().getValue()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("플레이어가 비겼을 때 배팅 이익을 잘 계산한다.")
    void calculateBettingMoneyWhenTieTest() {
        siso.betMoney(new GamblingMoney(1000));
        Dealer dealer = gameBoard.getDealer();
        dealer.receiveCard(new Card(Shape.HEART, Rank.JACK));
        dealer.receiveCard(new Card(Shape.HEART, Rank.SIX));

        gameBoard.calculateBettingMoney(gameBoard.calculateVictory());

        assertThat(siso.getMoney().getValue()).isEqualTo(0);
        assertThat(dealer.getMoney().getValue()).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어가 졌을 때 배팅 이익을 잘 계산한다.")
    void calculateBettingMoneyWhenLoseTest() {
        siso.betMoney(new GamblingMoney(1000));
        Dealer dealer = gameBoard.getDealer();
        dealer.receiveCard(new Card(Shape.HEART, Rank.JACK));
        dealer.receiveCard(new Card(Shape.HEART, Rank.EIGHT));

        gameBoard.calculateBettingMoney(gameBoard.calculateVictory());

        assertThat(siso.getMoney().getValue()).isEqualTo(-1000);
        assertThat(dealer.getMoney().getValue()).isEqualTo(1000);
    }
}

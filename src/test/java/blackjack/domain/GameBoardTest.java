package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Rank;
import blackjack.domain.cards.Shape;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
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
    @DisplayName("플레이어가 모두 패배한 테스트")
    void victoryLoseTest() {
        Dealer dealer = gameBoard.getDealer();
        dealer.receiveCard(new Card(Shape.HEART, Rank.ACE));
        dealer.receiveCard(new Card(Shape.HEART, Rank.JACK));

        Map<Player, Boolean> victoryResult = gameBoard.calculateVictory();


        assertThat(victoryResult.get(siso)).isFalse();
        assertThat(victoryResult.get(takan)).isFalse();
    }

    @Test
    @DisplayName("플레이어의 승리가 포함된 테스트")
    void victoryWinTest() {
        Dealer dealer = gameBoard.getDealer();
        dealer.receiveCard(new Card(Shape.HEART, Rank.QUEEN));
        dealer.receiveCard(new Card(Shape.HEART, Rank.JACK));

        Map<Player, Boolean> victoryResult = gameBoard.calculateVictory();

        assertThat(victoryResult.get(takan)).isTrue();
        assertThat(victoryResult.get(siso)).isFalse();
    }
}

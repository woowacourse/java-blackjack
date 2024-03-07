package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Shape;
import domain.participants.Name;
import domain.participants.Player;
import domain.participants.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameBoardTest {

    @Test
    @DisplayName("52장 카드 생성한다.")
    void makeAllCardTest() {
        List<Player> participantList = List.of(new Player(new Name("시소")), new Player(new Name("타칸")));
        Players players = new Players(participantList);
        Player dealer = new Player(new Name("딜러"));
        GameBoard gameBoard = new GameBoard(dealer, players);

        int result = gameBoard.getAllCardDeck().size();

        assertThat(result).isEqualTo(52);
    }

    @Test
    @DisplayName("플레이어의 수를 센다.")
    void countPlayerTest() {
        List<Player> participantList = List.of(new Player(new Name("시소")), new Player(new Name("타칸")));
        Players players = new Players(participantList);
        Player dealer = new Player(new Name("딜러"));
        GameBoard gameBoard = new GameBoard(dealer, players);

        int result = gameBoard.countPlayers();

        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어가 카드를 더 가질 수 있다.")
    void isPlayerNotOverTest() {
        Player siso = new Player(new Name("시소"));
        Player takan = new Player(new Name("타칸"));
        List<Player> participantList = List.of(siso, takan);
        Players players = new Players(participantList);
        Player dealer = new Player(new Name("딜러"));
        GameBoard gameBoard = new GameBoard(dealer, players);

        siso.receiveCard(new Card(Shape.HEART, Rank.SEVEN));
        siso.receiveCard(new Card(Shape.HEART, Rank.SIX));

        boolean result = gameBoard.isPlayerNotOver(0);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("딜러가 카드를 더 가질 수 있다.")
    void isDealerNotOverTest() {
        Player siso = new Player(new Name("시소"));
        Player takan = new Player(new Name("타칸"));
        List<Player> participantList = List.of(siso, takan);
        Players players = new Players(participantList);
        Player dealer = new Player(new Name("딜러"));
        GameBoard gameBoard = new GameBoard(dealer, players);

        dealer.receiveCard(new Card(Shape.HEART, Rank.SEVEN));
        dealer.receiveCard(new Card(Shape.HEART, Rank.SIX));

        boolean result = gameBoard.isDealerNotOver();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("초기에 딜러와 플레이어는 카드 두 장을 받는다.")
    void initialDistributeTest() {
        Player siso = new Player(new Name("시소"));
        Player takan = new Player(new Name("타칸"));
        List<Player> participantList = List.of(siso, takan);
        Players players = new Players(participantList);
        Player dealer = new Player(new Name("딜러"));
        GameBoard gameBoard = new GameBoard(dealer, players);

        gameBoard.initialDistribute();

        assertThat(dealer.getDeck().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어가 카드 한 장을 더 받는다.")
    void addCardToPlayerTest() {
        Player siso = new Player(new Name("시소"));
        Player takan = new Player(new Name("타칸"));
        List<Player> participantList = List.of(siso, takan);
        Players players = new Players(participantList);
        Player dealer = new Player(new Name("딜러"));
        GameBoard gameBoard = new GameBoard(dealer, players);

        siso.receiveCard(new Card(Shape.HEART, Rank.SEVEN));
        siso.receiveCard(new Card(Shape.HEART, Rank.SIX));
        gameBoard.addCardToPlayer(0);

        assertThat(siso.getDeck().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러가 카드 한 장을 더 받는다.")
    void addCardToDealerTest() {
        Player siso = new Player(new Name("시소"));
        Player takan = new Player(new Name("타칸"));
        List<Player> participantList = List.of(siso, takan);
        Players players = new Players(participantList);
        Player dealer = new Player(new Name("딜러"));
        GameBoard gameBoard = new GameBoard(dealer, players);

        dealer.receiveCard(new Card(Shape.HEART, Rank.SEVEN));
        dealer.receiveCard(new Card(Shape.HEART, Rank.SIX));
        gameBoard.addCardToDealer();

        assertThat(dealer.getDeck().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어가 모두 패배한 테스트")
    void victoryLoseTest() {
        Player siso = new Player(new Name("시소"));
        Player takan = new Player(new Name("타칸"));
        List<Player> participantList = List.of(siso, takan);
        Players players = new Players(participantList);
        Player dealer = new Player(new Name("딜러"));


        siso.receiveCard(new Card(Shape.HEART, Rank.TEN));
        takan.receiveCard(new Card(Shape.HEART, Rank.JACK));
        dealer.receiveCard(new Card(Shape.HEART, Rank.ACE));

        GameBoard gameBoard = new GameBoard(dealer, players);

        Map<Player, Boolean> victoryResult = gameBoard.calculateVictory();


        assertThat(victoryResult.get(siso)).isFalse();
        assertThat(victoryResult.get(takan)).isFalse();
    }

    @Test
    @DisplayName("플레이어의 승리가 포함된 테스트")
    void victoryWinTest() {
        Player siso = new Player(new Name("시소"));
        Player tacan = new Player(new Name("타칸"));
        Player dealer = new Player(new Name("딜러"));
        List<Player> participantList = List.of(siso, tacan);

        siso.receiveCard(new Card(Shape.HEART, Rank.FIVE));
        tacan.receiveCard(new Card(Shape.HEART, Rank.ACE));
        dealer.receiveCard(new Card(Shape.HEART, Rank.NINE));

        Players players = new Players(participantList);
        GameBoard gameBoard = new GameBoard(dealer, players);

        Map<Player, Boolean> victoryResult = gameBoard.calculateVictory();

        assertThat(victoryResult.get(tacan)).isTrue();
        assertThat(victoryResult.get(siso)).isFalse();
    }
}

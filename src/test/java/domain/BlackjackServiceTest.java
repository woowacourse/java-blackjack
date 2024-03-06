package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackServiceTest {

    @Test
    @DisplayName("52장 카드 생성한다.")
    void makeAllCardTest() {
        List<Player> participantList = List.of(new Player(new Name("시소")), new Player(new Name("타칸")));
        Player dealer = new Player(new Name("딜러"));
        BlackjackService blackjackService = new BlackjackService(dealer, participantList);

        int result = blackjackService.getAllCardDeck().size();

        assertThat(result).isEqualTo(52);
    }

    @Test
    @DisplayName("플레이어의 수를 센다.")
    void countPlayerTest() {
        List<Player> participantList = List.of(new Player(new Name("시소")), new Player(new Name("타칸")));
        Player dealer = new Player(new Name("딜러"));
        BlackjackService blackjackService = new BlackjackService(dealer, participantList);

        int result = blackjackService.countPlayers();

        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어가 카드를 더 가질 수 있다.")
    void isPlayerNotOverTest() {
        Player siso = new Player(new Name("시소"));
        Player takan = new Player(new Name("타칸"));
        List<Player> participantList = List.of(siso, takan);
        Player dealer = new Player(new Name("딜러"));

        BlackjackService blackjackService = new BlackjackService(dealer, participantList);
        siso.receiveCard(new Card(Shape.HEART, Rank.SEVEN));
        siso.receiveCard(new Card(Shape.HEART, Rank.SIX));

        boolean result = blackjackService.isPlayerNotOver(0);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("딜러가 카드를 더 가질 수 있다.")
    void isDealerNotOverTest() {
        Player siso = new Player(new Name("시소"));
        Player takan = new Player(new Name("타칸"));
        List<Player> participantList = List.of(siso, takan);
        Player dealer = new Player(new Name("딜러"));
        BlackjackService blackjackService = new BlackjackService(dealer, participantList);

        dealer.receiveCard(new Card(Shape.HEART, Rank.SEVEN));
        dealer.receiveCard(new Card(Shape.HEART, Rank.SIX));

        boolean result = blackjackService.isDealerNotOver();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("초기에 딜러와 플레이어는 카드 두 장을 받는다.")
    void initialDistributeTest() {
        List<Player> participantList = List.of(new Player(new Name("시소")), new Player(new Name("타칸")));
        Player dealer = new Player(new Name("딜러"));
        BlackjackService blackjackService = new BlackjackService(dealer, participantList);

        blackjackService.initialDistribute();

        assertThat(dealer.getDeck().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어가 카드 한 장을 더 받는다.")
    void addCardToPlayerTest() {
        Player siso = new Player(new Name("시소"));
        Player takan = new Player(new Name("타칸"));
        List<Player> participantList = List.of(siso, takan);
        Player dealer = new Player(new Name("딜러"));
        BlackjackService blackjackService = new BlackjackService(dealer, participantList);

        siso.receiveCard(new Card(Shape.HEART, Rank.SEVEN));
        siso.receiveCard(new Card(Shape.HEART, Rank.SIX));
        blackjackService.addCardToPlayer(0);

        assertThat(siso.getDeck().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러가 카드 한 장을 더 받는다.")
    void addCardToDealerTest() {
        Player siso = new Player(new Name("시소"));
        Player takan = new Player(new Name("타칸"));
        List<Player> participantList = List.of(siso, takan);
        Player dealer = new Player(new Name("딜러"));
        BlackjackService blackjackService = new BlackjackService(dealer, participantList);

        dealer.receiveCard(new Card(Shape.HEART, Rank.SEVEN));
        dealer.receiveCard(new Card(Shape.HEART, Rank.SIX));
        blackjackService.addCardToDealer();

        assertThat(dealer.getDeck().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어가 모두 패배한 테스트")
    void victoryLoseTest() {
        Player siso = new Player(new Name("시소"));
        Player tacan = new Player(new Name("타칸"));
        Player dealer = new Player(new Name("딜러"));
        List<Player> participantList = List.of(siso, tacan);

        siso.receiveCard(new Card(Shape.HEART, Rank.TEN));
        tacan.receiveCard(new Card(Shape.HEART, Rank.JACK));
        dealer.receiveCard(new Card(Shape.HEART, Rank.ACE));
        BlackjackService blackjackService = new BlackjackService(dealer, participantList);

        Map<Player, Boolean> victoryResult = blackjackService.calculateVictory();


        assertThat(victoryResult.get(siso)).isFalse();
        assertThat(victoryResult.get(tacan)).isFalse();
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
        BlackjackService blackjackService = new BlackjackService(dealer, participantList);

        Map<Player, Boolean> victoryResult = blackjackService.calculateVictory();

        assertThat(victoryResult.get(tacan)).isTrue();
        assertThat(victoryResult.get(siso)).isFalse();
    }
}

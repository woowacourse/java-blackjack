package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackServiceTest {

    @Test
    @DisplayName("52장 카드 생성") // TODO 바꿈
    void t() { // TODO 메서드이름 바꿈
        List<Player> participantList = List.of(new Player(new Name("시소")), new Player(new Name("타칸")));
        Player dealer = new Player(new Name("딜러"));
        BlackjackService blackjackService = new BlackjackService(dealer, participantList);

        int result = blackjackService.getAllCardDeck().getCards().size();

        assertThat(result).isEqualTo(52);
    }


    @Test
    @DisplayName("초기에 딜러와 플레이어는 카드 두 장을 받는다.")
    void initialSetting() {
        List<Player> participantList = List.of(new Player(new Name("시소")), new Player(new Name("타칸")));
        Player dealer = new Player(new Name("딜러"));
        BlackjackService blackjackService = new BlackjackService(dealer, participantList);

        blackjackService.initialDistribute();

        assertThat(dealer.getDeck().getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어가 모두 패배한 테스트")
    void victoryLossTest() {
        Player siso = new Player(new Name("시소"));
        Player tacan = new Player(new Name("타칸"));
        Player dealer = new Player(new Name("딜러"));
        List<Player> participantList = List.of(siso, tacan);

        siso.receiveCard(new Card(Shape.HEART, Rank.TEN));
        tacan.receiveCard(new Card(Shape.HEART, Rank.JACK));
        dealer.receiveCard(new Card(Shape.HEART, Rank.ACE));
        BlackjackService blackjackService = new BlackjackService(dealer, participantList);

        //Map<Player, Boolean> victoryResult = blackjackService.calculateVictory();


        //assertThat(victoryResult.get(siso)).isFalse();
        //assertThat(victoryResult.get(tacan)).isFalse();
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

        //Map<Player, Boolean> victoryResult = blackjackService.calculateVictory();


        //assertThat(victoryResult.get(tacan)).isTrue();
        //assertThat(victoryResult.get(siso)).isFalse();
    }

    @Test
    void distributeTest() {
        Player siso = new Player(new Name("시소"));
        Player tacan = new Player(new Name("타칸"));
        Player dealer = new Player(new Name("딜러"));
        List<Player> participantList = List.of(siso, tacan);
        BlackjackService blackjackService = new BlackjackService(dealer, participantList);

        blackjackService.initialDistribute();

        Assertions.assertThat(dealer.getDeck().size()).isEqualTo(siso.getDeck().size());
    }
}

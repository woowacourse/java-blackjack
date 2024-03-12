package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import blackjack.domain.participants.Hands;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.BlackJackGameResult;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameResultTest {

    private List<Player> players;

    @BeforeEach
    void beforeEach() {
        Player siso = new Player(new Name("시소"));
        Player takan = new Player(new Name("타칸"));

        Hands sisoHands = new Hands(List.of(
                new Card(Shape.HEART, Rank.JACK),
                new Card(Shape.HEART, Rank.EIGHT))
        ); // 18

        Hands takanHands = new Hands(List.of(
                new Card(Shape.SPADE, Rank.QUEEN),
                new Card(Shape.SPADE, Rank.JACK))
        ); // 20

        siso.receiveHands(sisoHands);
        takan.receiveHands(takanHands);

        players = List.of(siso, takan);
    }


    @Test
    @DisplayName("플레이어가 모두 패배한 테스트")
    void victoryLoseTest() {
        BlackJackGameResult blackJackGameResult = new BlackJackGameResult(players, 21);

        assertThat(blackJackGameResult.countDealerWin()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어의 승리가 포함된 테스트")
    void victoryWinTest() {
        BlackJackGameResult blackJackGameResult = new BlackJackGameResult(players, 17);

        assertThat(blackJackGameResult.countDealerWin()).isEqualTo(0);
    }

    @Test
    @DisplayName("한명의 플레이어만 승리한 테스트")
    void victorySingleWinTest() {
        BlackJackGameResult blackJackGameResult = new BlackJackGameResult(players, 19);

        assertThat(blackJackGameResult.countDealerWin()).isEqualTo(1);
    }
}

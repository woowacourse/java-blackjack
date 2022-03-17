package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.util.BlackjackTestUtil;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GameResultTest {

    @ParameterizedTest
    @CsvSource(value = {"21,1500", "20,1000", "19,0", "18,-1000"})
    @DisplayName("수익을 Map에 저장한다")
    void saveIntoMap(int playerScore, int expected) {
        // given
        Dealer dealer = BlackjackTestUtil.createDealer(19);

        Player player = BlackjackTestUtil.createPlayer(playerScore);
        List<Player> players = List.of(player);

        // when
        GameResult gameResult = GameResult.of(dealer, players);
        Map<String, Integer> profits = gameResult.getProfits();

        // then
        assertThat(profits.get(player.getName())).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 수익은 모든 플레이어 수익의 합의 반대다.")
    void dealerProfit() {
        // given
        Dealer dealer = BlackjackTestUtil.createDealer(19);

        List<Card> cards = BlackjackTestUtil.createCards(20);
        Player player1 = new Player(new Name("player1"), cards, new Betting(1000));
        Player player2 = new Player(new Name("player2"), cards, new Betting(1000));
        List<Player> players = List.of(player1, player2);

        // when
        GameResult gameResult = GameResult.of(dealer, players);

        // then
        assertThat(gameResult.getDealerProfit()).isEqualTo(-2000);
    }
}

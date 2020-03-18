package first.domain.gamer;

import first.domain.card.Card;
import first.domain.card.Rank;
import first.domain.card.Suit;
import first.domain.card.providable.CardDeck;
import first.domain.result.PlayerResult;
import first.domain.result.WinLose;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllGamersTest {
    @Test
    void 첫_페이즈_드로우_테스트() {
        Player player1 = new Player("phobi");
        Player player2 = new Player("jason");

        List<Player> players = Arrays.asList(player1, player2);

        AllGamers allGamers = new AllGamers(players);
        allGamers.drawFirstPhase(new CardDeck());

        for (Gamer gamer : allGamers.getGamers()) {
            int cardAmount = gamer.getCardsOnHand().getCards().size();
            assertThat(cardAmount).isEqualTo(2);
        }
    }

    @Test
    void 결과_찾기_테스트() {
        Dealer dealer = new Dealer();
        Player player1 = new Player("phobi");
        Player player2 = new Player("jason");

        makePlayersDrawFixedCards(dealer, 10, 5);
        makePlayersDrawFixedCards(player1, 2, 3);
        makePlayersDrawFixedCards(player2, 10, 6);

        List<Player> players = Arrays.asList(player1, player2);

        AllGamers allGamers = new AllGamers(dealer, players);

        assertThat(allGamers.determineResults().extractPlayerResults()).contains(
                new PlayerResult("phobi", WinLose.LOSE),
                new PlayerResult("jason", WinLose.WIN));

        // 딜러 확인
        Map<WinLose, Integer> expectedDealerWinLose = new HashMap<>();
        expectedDealerWinLose.put(WinLose.WIN, 1);
        expectedDealerWinLose.put(WinLose.LOSE, 1);

        assertThat(allGamers.determineResults().extractDealerResult().getWinLose()).isEqualTo(expectedDealerWinLose);
    }

    private void makePlayersDrawFixedCards(Gamer gamer, int... ranks) {
        for (int rank : ranks) {
            gamer.drawCard(() -> Card.of(Rank.of(rank), Suit.CLOVER));
        }
    }
}

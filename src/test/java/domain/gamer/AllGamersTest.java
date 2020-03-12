package domain.gamer;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.card.providable.CardDeck;
import domain.result.DealerResult;
import domain.result.UserResult;
import domain.result.WinLose;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllGamersTest {
    @Test
    void 첫_페이즈_드로우_테스트() {
        Dealer dealer = new Dealer();
        Player player1 = new Player("phobi");
        Player player2 = new Player("jason");

        List<Player> players = Arrays.asList(player1, player2);

        AllGamers allGamers = new AllGamers(dealer, players);
        allGamers.drawFirstPhase(new CardDeck());

        for (Gamer gamer : allGamers.getGamers()) {
            int cardAmount = gamer.getCardsOnHand().getCardAmount();
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

        assertThat(allGamers.determineResults()).contains(
                new UserResult("phobi", WinLose.LOSE),
                new UserResult("jason", WinLose.WIN));

        // 딜러 확인
        Map<WinLose, Integer> expectedDealerWinLose = new HashMap<>();
        expectedDealerWinLose.put(WinLose.WIN, 1);
        expectedDealerWinLose.put(WinLose.LOSE, 1);

        assertThat(allGamers.determineResults().stream()
                .filter(a -> a instanceof DealerResult)
                .findAny()
                .get()
                .getWinLose()).isEqualTo(expectedDealerWinLose);
    }

    private void makePlayersDrawFixedCards(Gamer gamer, int... ranks) {
        for (int rank : ranks) {
            gamer.drawCard(() -> Card.of(Rank.of(rank), Suit.CLOVER));
        }
    }
}

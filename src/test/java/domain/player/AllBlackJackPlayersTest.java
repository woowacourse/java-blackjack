package domain.player;

import domain.CardDeck;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.result.DealerResult;
import domain.result.UserResult;
import domain.result.WinLose;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBlackJackPlayersTest {
    @Test
    void 첫_페이즈_드로우_테스트() {
        Dealer dealer = new Dealer();
        User user1 = new User("phobi");
        User user2 = new User("jason");

        List<User> users = Arrays.asList(user1, user2);

        AllBlackJackPlayers allBlackJackPlayers = new AllBlackJackPlayers(dealer, users);
        allBlackJackPlayers.drawFirstPhase(new CardDeck());

        for (BlackJackPlayer blackJackPlayer : allBlackJackPlayers.getGamers()) {
            int cardAmount = blackJackPlayer.getCardsOnHand().getCardAmount();
            assertThat(cardAmount).isEqualTo(2);
        }
    }

    @Test
    void 결과_찾기_테스트() {
        Dealer dealer = new Dealer();
        User user1 = new User("phobi");
        User user2 = new User("jason");

        makePlayersDrawFixedCards(dealer, 10, 5);
        makePlayersDrawFixedCards(user1, 2, 3);
        makePlayersDrawFixedCards(user2, 10, 6);

        List<User> users = Arrays.asList(user1, user2);

        AllBlackJackPlayers allBlackJackPlayers = new AllBlackJackPlayers(dealer, users);

        assertThat(allBlackJackPlayers.determineResults()).contains(
                new UserResult("phobi", WinLose.LOSE),
                new UserResult("jason", WinLose.WIN));

        // 딜러 확인
        Map<WinLose, Integer> expectedDealerWinLose = new HashMap<>();
        expectedDealerWinLose.put(WinLose.WIN, 1);
        expectedDealerWinLose.put(WinLose.LOSE, 1);

        assertThat(allBlackJackPlayers.determineResults().stream()
                .filter(a -> a instanceof DealerResult)
                .findAny()
                .get()
                .getWinLose()).isEqualTo(expectedDealerWinLose);
    }

    private void makePlayersDrawFixedCards(BlackJackPlayer blackJackPlayer, int... ranks) {
        for (int rank : ranks) {
            blackJackPlayer.drawCard(() -> Card.of(Rank.of(rank), Suit.CLOVER));
        }
    }
}

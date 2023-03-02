package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackTest {

    private static class ZeroIndexGenerator implements CardIndexGenerator {

        @Override
        public int chooseIndex(int deckSize) {
            return 0;
        }
    }

    @DisplayName("플레이어의 승부 결과를 반환한다")
    @Test
    void calculateGameResults() {
        Users users = Users.from(List.of("hongo", "kiara"));
        BlackJack blackJack = BlackJack.of(users, new ZeroIndexGenerator());
        List<Player> players = users.getPlayers();

        Player player1 = players.get(0);
        Player player2 = players.get(1);

        // 카드 현황
        // player1 : ACE(11), 2 => 13
        // player2 : 3, 4       => 7
        // dealer  : 5, 6       => 11
        Map<Player, GameResult> gameResults = blackJack.calculateGameResults();
        assertThat(gameResults.get(player1)).isEqualTo(GameResult.WIN);
        assertThat(gameResults.get(player2)).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("플레이어와 딜러의 점수가 같을 경우 무승부(PUSH)를 반환한다")
    @Test
    void calculateGameResults_PUSH() {
        Users users = Users.from(List.of("hongo"));
        BlackJack blackJack = BlackJack.of(users, new ZeroIndexGenerator());
        List<Player> players = users.getPlayers();

        Player player = players.get(0);
        Dealer dealer = users.getDealer();
        dealer.hit(new Card(Denomination.SIX, Suits.DIAMOND));

        // 카드 현황
        // player : ACE(11), 2 => 13
        // dealer : 3, 4, 6    => 13
        Map<Player, GameResult> gameResults = blackJack.calculateGameResults();
        assertThat(gameResults.get(player)).isEqualTo(GameResult.PUSH);
    }

    @DisplayName("딜러와 플레이어의 카드가 21 초과일 경우 무승부를 반환한다")
    @Test
    void PUSH_whenBothCardsOver21() {
        Users users = Users.from(List.of("hongo"));
        BlackJack blackJack = BlackJack.of(users, new ZeroIndexGenerator());
        List<Player> players = users.getPlayers();

        Player player = players.get(0);
        Dealer dealer = users.getDealer();
        player.hit(new Card(Denomination.JACK, Suits.HEART));
        player.hit(new Card(Denomination.QUEEN, Suits.HEART));
        dealer.hit(new Card(Denomination.FIVE, Suits.DIAMOND));
        dealer.hit(new Card(Denomination.JACK, Suits.DIAMOND));

        // 카드 현황
        // player : ACE(1), 2, 10, 10  => 23
        // dealer : 3, 4, 5, 10        => 22
        System.out.println(player.getScore());
        System.out.println(dealer.getScore());
        Map<Player, GameResult> gameResults = blackJack.calculateGameResults();
        assertThat(gameResults.get(player)).isEqualTo(GameResult.PUSH);
    }
}

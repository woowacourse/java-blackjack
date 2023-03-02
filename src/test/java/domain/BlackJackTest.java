package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackTest {

    @DisplayName("플레이어의 승부 결과를 반환한다")
    @Test
    void calculateGameResults() {
        Users users = Users.from(List.of("hongo", "kiara"));
        BlackJack blackJack = new BlackJack(users, new RandomCardIndexGenerator());
        List<Player> players = users.getPlayers();

        Player player1 = players.get(0);
        Player player2 = players.get(1);

        player1.hit(new Card(Denomination.SIX, Suits.HEART));
        player2.hit(new Card(Denomination.JACK, Suits.HEART));
        users.getDealer().hit(new Card(Denomination.SEVEN, Suits.HEART));

        Map<Player, GameResult> gameResults = blackJack.calculateGameResults();
        assertThat(gameResults.get(player1)).isEqualTo(GameResult.LOSE);
        assertThat(gameResults.get(player2)).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어와 딜러의 점수가 같을 경우 무승부(PUSH)를 반환한다")
    @Test
    void calculateGameResults_PUSH() {
        Users users = Users.from(List.of("hongo"));
        BlackJack blackJack = new BlackJack(users, new RandomCardIndexGenerator());
        List<Player> players = users.getPlayers();

        Player player = players.get(0);
        Dealer dealer = users.getDealer();
        player.hit(new Card(Denomination.FOUR, Suits.HEART));
        player.hit(new Card(Denomination.SIX, Suits.HEART));
        dealer.hit(new Card(Denomination.JACK, Suits.DIAMOND));

        Map<Player, GameResult> gameResults = blackJack.calculateGameResults();
        assertThat(gameResults.get(player)).isEqualTo(GameResult.PUSH);
    }

    @DisplayName("딜러와 플레이어의 카드가 21 초과일 경우 무승부를 반환한다")
    @Test
    void PUSH_whenBothCardsOver21() {
        Users users = Users.from(List.of("hongo"));
        BlackJack blackJack = new BlackJack(users, new RandomCardIndexGenerator());
        List<Player> players = users.getPlayers();

        Player player = players.get(0);
        Dealer dealer = users.getDealer();
        player.hit(new Card(Denomination.SEVEN, Suits.HEART));
        player.hit(new Card(Denomination.SIX, Suits.HEART));
        player.hit(new Card(Denomination.JACK, Suits.HEART));
        dealer.hit(new Card(Denomination.JACK, Suits.DIAMOND));
        dealer.hit(new Card(Denomination.QUEEN, Suits.DIAMOND));
        dealer.hit(new Card(Denomination.TWO, Suits.DIAMOND));

        Map<Player, GameResult> gameResults = blackJack.calculateGameResults();
        assertThat(gameResults.get(player)).isEqualTo(GameResult.PUSH);
    }
}

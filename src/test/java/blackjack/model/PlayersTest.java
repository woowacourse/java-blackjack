package blackjack.model;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("중복된 이름이 있으면 예외 발생")
    void test_fail_duplicate_name() {
        List<Player> allPlayers = List.of(new Player("pobi", 1000), new Player("pobi", 1000));

        assertThatThrownBy(() -> new Players(allPlayers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("게임 결과 계산")
    void test_calculate_game_result() {
        List<Player> allPlayers = List.of(new Player("pobi", 1000));

        Players players = new Players(allPlayers);

        for (Player player : players.all()) {
            player.addCard(new Card(Suit.HEART, Rank.JACK));
        }

        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Suit.HEART, Rank.JACK));

        List<GameSummary> gameSummaries = players.calculateGameResult(dealer);

        for (GameSummary gameSummary : gameSummaries) {
            assertThat(gameSummary.score()).isEqualTo(10);
        }
    }

}

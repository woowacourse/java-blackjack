package blackjack.domain.participant;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.GameSummary;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    private Players players;
    private Dealer dealer;

    @BeforeEach
    void setup() {
        players = new Players(List.of(new Player("pobi", 1000)));

        dealer = new Dealer();
        dealer.addCard(new Card(Suit.HEART, Rank.JACK));
    }

    @Test
    @DisplayName("중복된 이름이 있으면 예외 발생")
    void test_fail_duplicate_name() {
        List<Player> allPlayers = List.of(new Player("pobi", 1000), new Player("pobi", 1000));

        assertThatThrownBy(() -> new Players(allPlayers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어 인원 7명 초과할 경우 예외 발생")
    void test_fail_player_count_over() {
        List<String> playerNames = List.of("one", "two", "three", "four", "five", "six", "seven", "eight");
        List<Player> allPlayers = new ArrayList<>();
        for (String playerName : playerNames) {
            allPlayers.add(new Player(playerName, 1000));
        }

        assertThatThrownBy(() -> new Players(allPlayers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("게임 결과 계산")
    void test_calculate_game_summary() {

        for (Player player : players.all()) {
            player.addCard(new Card(Suit.HEART, Rank.JACK));
        }

        List<GameSummary> gameSummaries = players.calculateGameSummaries(dealer);

        for (GameSummary gameSummary : gameSummaries) {
            assertThat(gameSummary.score()).isEqualTo(10);
        }
    }

    @Test
    @DisplayName("게임 수익 계산")
    void test_calculate_game_result() {

        for (Player player : players.all()) {
            player.addCard(new Card(Suit.HEART, Rank.ACE));
        }

        List<GameResult> gameResults = players.calculateGameResults(dealer);

        assertThat(gameResults)
                .extracting(GameResult::name, GameResult::profit)
                .containsExactly(
                        tuple("딜러", -1000),
                        tuple("pobi", 1000)
                );
    }

}

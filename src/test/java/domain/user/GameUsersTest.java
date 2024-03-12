package domain.user;

import static domain.card.Number.EIGHT;
import static domain.card.Number.FIVE;
import static domain.card.Number.SEVEN;
import static domain.card.Number.SIX;
import static domain.card.Shape.CLUB;
import static domain.game.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import domain.Deck;
import domain.card.Card;
import domain.game.PlayerResults;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameUsersTest {
    @Test
    @DisplayName("승패 결과를 반환한다.")
    void generatePlayerResultTest() {
        Player player1 = new Player(new Name("a"));
        GameUsers gameUsers = new GameUsers(new Players(List.of(player1)), new Deck(List.of(
                new Card(CLUB, FIVE),
                new Card(CLUB, SIX),
                new Card(CLUB, SEVEN),
                new Card(CLUB, EIGHT)
        )));

        PlayerResults playerResults = gameUsers.generatePlayerResults();

        assertThat(playerResults.getPlayerResults()).containsExactly(Map.entry(player1, WIN));
    }
}

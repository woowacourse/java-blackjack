import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @DisplayName("")
    @Test
    void giveInitCardsSuccessTest() {
        Players players = new Players(List.of(
                new Player(new PlayerName("pobi")),
                new Player(new PlayerName("crong"))
        ));
        BlackjackGame blackjackGame = new BlackjackGame(players);

        blackjackGame.giveInitCards();

        Assertions.assertThat(players.getPlayers()).extracting("cards")
                .asList()
                .hasSize(2);
    }

}

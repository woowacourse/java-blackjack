package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewGame() {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            players.add(new Player(new Name("newName")));
        }
        GamePlayer gamePlayer = new GamePlayer(new Players(players), new Dealer());

        assertDoesNotThrow(() -> new Game(new Deck(), gamePlayer));
    }
}

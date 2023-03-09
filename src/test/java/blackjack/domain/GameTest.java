package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewGame() {
        GamePlayer gamePlayer = new GamePlayer(Players.of(List.of("name1", "name2", "name3")), new Dealer());
        Game game = new Game(new Deck(), gamePlayer);

        assertThat(game.getPlayersCount()).isEqualTo(3);
    }
}

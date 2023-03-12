package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    @DisplayName("Game의 참가자의 수는 입력된 참가자의 수와 같다.")
    @Test
    void Should_Create_When_NewGame() {
        GamePlayer gamePlayer = new GamePlayer(new Dealer(), Players.from(List.of("name1", "name2", "name3")));
        Game game = Game.from(gamePlayer);

        assertThat(game.getPlayersCount()).isEqualTo(3);
    }
}

package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamePlayerTest {
    private GamePlayer gamePlayer;

    @BeforeEach
    void init() {
        gamePlayer = new GamePlayer(new Dealer(), Players.from(List.of("name1", "name2", "name3")));
    }

    @DisplayName("GamePlayer의 수는 players의 수보다 하나 많다.")
    @Test
    void Should_Create_When_NewGamePlayer() {
        assertThat(gamePlayer.getCount()).isEqualTo(4);
    }
}

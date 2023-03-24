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

    @DisplayName("GamePlayer는 Players를 가진다.")
    @Test
    void Should_HasPlayers_When_NewGamePlayer() {
        assertThat(gamePlayer.getPlayers()).isInstanceOf(Players.class);
    }

    @DisplayName("GamePlayer는 Dealer를 가진다.")
    @Test
    void Should_HasDealer_When_NewGamePlayer() {
        assertThat(gamePlayer.getDealer()).isInstanceOf(Dealer.class);
    }
}

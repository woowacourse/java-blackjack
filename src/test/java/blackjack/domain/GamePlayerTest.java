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
        Name name1 = new Name("name");
        Name name2 = new Name("name2");
        Name name3 = new Name("name3");

        BettingAmount bettingAmount = new BettingAmount(1000);

        Player player1 = new Player(name1, bettingAmount);
        Player player2 = new Player(name2, bettingAmount);
        Player player3 = new Player(name3, bettingAmount);

        gamePlayer = new GamePlayer(new Dealer(), new Players(List.of(player1, player2, player3)));
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

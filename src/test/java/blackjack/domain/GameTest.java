package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    @DisplayName("딜러는 게임이 시작되면 카드 2장을 제공받는다.")
    @Test
    void Should_Create_When_NewGame() {
        GamePlayer gamePlayer = new GamePlayer(new Dealer(), Players.from(List.of("name1", "name2", "name3")));
        Game game = Game.from(gamePlayer);
        Dealer dealer = game.getDealer();

        assertThat(dealer.getAllCards().size()).isEqualTo(2);
    }
}

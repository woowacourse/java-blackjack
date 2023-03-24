package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    @DisplayName("딜러는 게임이 시작되면 카드 2장을 제공받는다.")
    @Test
    void Should_Create_When_NewGame() {
        Name name1 = new Name("name");
        Name name2 = new Name("name2");
        Name name3 = new Name("name3");
        BettingAmount bettingAmount = new BettingAmount(1000);
        Player player1 = new Player(name1, bettingAmount);
        Player player2 = new Player(name2, bettingAmount);
        Player player3 = new Player(name3, bettingAmount);
        GamePlayer gamePlayer = new GamePlayer(new Dealer(), new Players(List.of(player1, player2, player3)));
        Game game = Game.from(gamePlayer);
        Dealer dealer = game.getDealer();

        assertThat(dealer.getAllCards()).hasSize(2);
    }
}

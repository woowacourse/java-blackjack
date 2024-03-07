package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GameTest {

    @Test
    @DisplayName("플레이어와 딜러의 승패를 반영한다.")
    void decideResult() {
        Dealer dealer = new Dealer();
        DealerCards dealerCards = new DealerCards(dealer, List.of(new Card(10, Shape.CLUB), new Card(6, Shape.CLUB)));

        Player player = new Player(new Name("capy"));
        PlayerCards playerCards = new PlayerCards(player, List.of(new Card(10, Shape.CLUB), new Card(11, Shape.CLUB)));

        Game game = new Game(new Rule());
        game.decideResult(dealerCards, List.of(playerCards));

        assertAll(
                () -> assertThat(dealer.getWinScore()).isEqualTo(0),
                () -> assertThat(dealer.getTieScore()).isEqualTo(0),
                () -> assertThat(dealer.getLoseScore()).isEqualTo(1),
                () -> assertThat(player.getWinScore()).isEqualTo(1),
                () -> assertThat(player.getTieScore()).isEqualTo(0),
                () -> assertThat(player.getLoseScore()).isEqualTo(0)
        );
    }
}

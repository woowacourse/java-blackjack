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

        Name capy = new Name("capy");
        Player player = new Player(capy);
        PlayerCards playerCards = new PlayerCards(player, List.of(new Card(10, Shape.CLUB), new Card(11, Shape.CLUB)));

        ScoreBoard scoreBoard = ScoreBoard.from(List.of(capy));
        Game game = new Game(new Rule(), scoreBoard);
        game.decideResult(dealerCards, List.of(playerCards));

        assertAll(
                () -> assertThat(scoreBoard.getDealerScore().getWinScore()).isEqualTo(0),
                () -> assertThat(scoreBoard.getDealerScore().getTieScore()).isEqualTo(0),
                () -> assertThat(scoreBoard.getDealerScore().getLoseScore()).isEqualTo(1),
                () -> assertThat(scoreBoard.getPlayerScore().get(capy)).isEqualTo(Status.WIN)
        );
    }
}

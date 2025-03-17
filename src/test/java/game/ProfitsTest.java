package game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ProfitsTest {

    @Test
    void 모든_플레이어의_수익을_계산한다() {
        //given
        List<Player> players = List.of(
                new Player("a", 1000),
                new Player("b", 1000),
                new Player("c", 1000),
                new Player("d", 1000));
        List<GameResult> gameResults = List.of(
                GameResult.BLACKJACK, GameResult.WIN, GameResult.DRAW, GameResult.LOSE);

        //when
        Profits profits = Profits.of(players, gameResults);

        //then
        assertThat(profits.getProfits()).containsExactlyElementsOf(List.of(1500, 1000, 0, -1000));
    }

    @Test
    void 딜러의_수익을_계산한다() {
        //given
        Player player1 = new Player("a", 1000);
        Player player2 = new Player("b", 1000);
        Player player3 = new Player("c", 1000);
        Player player4 = new Player("d", 1000);
        List<Player> players = List.of(player1, player2, player3, player4);

        List<GameResult> gameResults = List.of(
                GameResult.BLACKJACK, GameResult.WIN, GameResult.DRAW, GameResult.LOSE);

        Profits profits = Profits.of(players, gameResults);

        //when
        int dealerProfit = profits.evaluateDealerProfit();

        //then
        assertThat(dealerProfit).isEqualTo(-1500);
    }

}

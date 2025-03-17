package game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ProfitsTest {

    @Test
    void 베팅금에_따른_수익을_계산한다() {
        List<Integer> bettingMoneys = List.of(1000, 1000, 1000, 1000);
        List<GameResult> gameResults = List.of(
                GameResult.BLACKJACK, GameResult.WIN, GameResult.DRAW, GameResult.LOSE);

        Profits profits = Profits.of(bettingMoneys, gameResults);

        assertThat(profits.getProfits()).containsExactlyElementsOf(List.of(1500, 1000, 0, -1000));
    }

    @Test
    void 딜러의_수익을_계산한다() {
        List<Integer> bettingMoneys = List.of(1000, 1000, 1000, 1000);
        List<GameResult> gameResults = List.of(
                GameResult.BLACKJACK, GameResult.WIN, GameResult.DRAW, GameResult.LOSE);
        Profits profits = Profits.of(bettingMoneys, gameResults);

        int dealerProfit = profits.evaluateDealerProfit();
        
        assertThat(dealerProfit).isEqualTo(-1500);
    }

}

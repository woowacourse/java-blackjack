package blackjack.domain.result;

import blackjack.domain.result.user.*;
import blackjack.domain.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitTest {

    @ParameterizedTest
    @MethodSource("createUsersAndProfit")
    void of(User player, User dealer, double profitRate) {
        int profit = (int) (1000 * profitRate);
        assertThat(player.calculateProfit(dealer).getProfit()).isEqualTo(profit);
    }

    private static Stream<Arguments> createUsersAndProfit() {
        return Stream.of(
                Arguments.of(new BlackjackPlayer(), new ScoreTwentyDealer(), 1.5),
                Arguments.of(new BlackjackPlayer(), new BlackjackDealer(), 0.0),
                Arguments.of(new BustPlayer(), new ScoreTwentyDealer(), -1.0),
                Arguments.of(new BustPlayer(), new BustDealer(), -1.0),
                Arguments.of(new ScoreNineteenPlayer(), new ScoreEighteenDealer(), 1.0),
                Arguments.of(new ScoreNineteenPlayer(), new ScoreNineteenDealer(), 0.0),
                Arguments.of(new ScoreNineteenPlayer(), new ScoreTwentyDealer(), -1.0)
        );
    }

    @Test
    void sum() {
        List<Profit> profits = Arrays.asList(
                new Profit(1000),
                new Profit(2000),
                new Profit(3000)
        );

        assertThat(Profit.sum(profits)).isEqualTo(new Profit(6000));
    }

    @Test
    void opposite() {
        Profit profit = new Profit(1000);

        assertThat(profit.opposite()).isEqualTo(new Profit(-1000));
    }
}
package blackjack.domain.result;

import blackjack.domain.playing.user.User;
import blackjack.domain.result.user.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitRateTest {

    @ParameterizedTest
    @MethodSource("createUsersAndProfitRate")
    void of(User createdPlayer, User createdDealer, double profitRate) {
        User player = createdPlayer;
        User dealer = createdDealer;

        assertThat(ProfitRate.of(player, dealer)).isEqualTo(profitRate);
    }

    private static Stream<Arguments> createUsersAndProfitRate() {
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
}
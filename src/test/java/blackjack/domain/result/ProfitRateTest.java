package blackjack.domain.result;

import blackjack.domain.playing.user.User;
import blackjack.domain.result.user.BlackjackDealer;
import blackjack.domain.result.user.BlackjackPlayer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitRateTest {

    @ParameterizedTest
    @MethodSource("createUsersAndProfitRate")
    void of(User createdDealer, User createdPlayer, double profitRate) {
        User dealer = createdDealer;
        User player = createdPlayer;

        assertThat(ProfitRate.of(player, dealer)).isEqualTo(profitRate);
    }

    private static Stream<Arguments> createUsersAndProfitRate() {
        return Stream.of(
                Arguments.of(new BlackjackDealer(), new BlackjackPlayer(), 1.0)
        );
    }
}
package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BlackjackPlayerRevenueRateTest {

    @ParameterizedTest
    @DisplayName("플레이어의 승패 상태에 따른 수익 배율을 가져온다.")
    @CsvSource(value = {"BLACK_JACK_WIN, 1.5",
            "WIN, 1",
            "DRAW, 0",
            "BLACK_JACK_LOSE, -1",
            "LOSE, -1"})
    void should_return_revenue_rate_by_player_winning_status(BlackJackWinningStatus status, double expected) {
        // when
        double result = BlackjackPlayerRevenueRate.getRate(status);

        // then
        assertThat(result).isEqualTo(expected);
    }
}

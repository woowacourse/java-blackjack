package blackjack.domain.participant.playerstatus;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StayTest {


    @ParameterizedTest
    @DisplayName("플레이어가 STAY이고, 딜러보다 점수가 낮을 때 상금을 계산한다.")
    @CsvSource(value = {"19:20:false:100:-100", "19:22:false:100:100"}, delimiter = ':')
    void calculateProfit_1(int playerScore, int dealerScore, boolean dealerBlackjack, int bettingAmount,
                           int expected) {
        // give
        final CalculableStatus status = Stay.getInstance();

        // when
        final double actual = status.calculateProfit(playerScore, dealerScore, dealerBlackjack, bettingAmount);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("플레이어가 STAY이고, 딜러보다 점수가 높을 때 상금을 계산한다.")
    @CsvSource(value = {"20:19:false:100:100", "21:19:false:100:100"}, delimiter = ':')
    void calculateProfit_2(int playerScore, int dealerScore, boolean dealerBlackjack, int bettingAmount,
                           int expected) {
        // give
        final CalculableStatus status = Stay.getInstance();

        // when
        final double actual = status.calculateProfit(playerScore, dealerScore, dealerBlackjack, bettingAmount);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("플레이어가 STAY이고, 딜러와 점수가 같을 때 상금을 계산한다.")
    @CsvSource(value = {"20:20:false:100:0", "21:21:false:100:0"}, delimiter = ':')
    void calculateProfit_3(int playerScore, int dealerScore, boolean dealerBlackjack, int bettingAmount,
                           int expected) {
        // give
        final CalculableStatus status = Stay.getInstance();

        // when
        final double actual = status.calculateProfit(playerScore, dealerScore, dealerBlackjack, bettingAmount);

        // then
        assertThat(actual).isEqualTo(expected);
    }

}

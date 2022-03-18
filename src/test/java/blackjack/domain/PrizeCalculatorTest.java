package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.PlayerStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PrizeCalculatorTest {

    @ParameterizedTest
    @DisplayName("적절한 계산기를 반환한다.")
    @CsvSource(value = {"BUST:BUST", "BLACKJACK:BLACKJACK", "STAY:DEFAULT"}, delimiter = ':')
    void from(PlayerStatus playerStatus, PrizeCalculator expected) {
        // when
        final PrizeCalculator actual = PrizeCalculator.from(playerStatus);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("플레이어아 BUST일 때 상금을 계산한다.")
    @CsvSource(value = {"20:false:100:-100", "21:true:200:-200", "21:false:300:-300",
            "22:false:400:-400"}, delimiter = ':')
    void calculatePrize_bust(int dealerScore, boolean dealerBlackjack, int bettingAmount, int expected) {
        // give
        final PrizeCalculator prizeCalculator = PrizeCalculator.from(PlayerStatus.BUST);

        // when
        final double actual = prizeCalculator.calculate(22, dealerScore, dealerBlackjack, bettingAmount);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("플레이어아 BLACKJACK일 때 상금을 계산한다.")
    @CsvSource(value = {"20:false:100:150", "21:true:200:0", "21:false:300:450", "22:false:400:600"}, delimiter = ':')
    void calculatePrize_blackjack(int dealerScore, boolean dealerBlackjack, int bettingAmount, int expected) {
        // give
        final PrizeCalculator prizeCalculator = PrizeCalculator.from(PlayerStatus.BLACKJACK);

        // when
        final double actual = prizeCalculator.calculate(21, dealerScore, dealerBlackjack, bettingAmount);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("플레이어아 STAY이고, 딜러보다 점수가 낮을 때 상금을 계산한다.")
    @CsvSource(value = {"19:20:false:100:-100", "19:22:false:100:100"}, delimiter = ':')
    void calculatePrize_default1(int playerScore, int dealerScore, boolean dealerBlackjack, int bettingAmount,
                                 int expected) {
        // give
        final PrizeCalculator prizeCalculator = PrizeCalculator.from(PlayerStatus.STAY);

        // when
        final double actual = prizeCalculator.calculate(playerScore, dealerScore, dealerBlackjack, bettingAmount);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("플레이어아 STAY이고, 딜러보다 점수가 높을 때 상금을 계산한다.")
    @CsvSource(value = {"20:19:false:100:100", "21:19:false:100:100"}, delimiter = ':')
    void calculatePrize_default2(int playerScore, int dealerScore, boolean dealerBlackjack, int bettingAmount,
                                 int expected) {
        // give
        final PrizeCalculator prizeCalculator = PrizeCalculator.from(PlayerStatus.STAY);

        // when
        final double actual = prizeCalculator.calculate(playerScore, dealerScore, dealerBlackjack, bettingAmount);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("플레이어아 STAY이고, 딜러와 점수가 같을 때 상금을 계산한다.")
    @CsvSource(value = {"20:20:false:100:0", "21:21:false:100:0"}, delimiter = ':')
    void calculatePrize_default3(int playerScore, int dealerScore, boolean dealerBlackjack, int bettingAmount,
                                 int expected) {
        // give
        final PrizeCalculator prizeCalculator = PrizeCalculator.from(PlayerStatus.STAY);

        // when
        final double actual = prizeCalculator.calculate(playerScore, dealerScore, dealerBlackjack, bettingAmount);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
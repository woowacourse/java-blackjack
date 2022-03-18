package blackjack.domain.prizecalculator;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.PlayerStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BustCalculatorTest {

    @ParameterizedTest
    @DisplayName("플레이어가 BUST일 때 상금을 계산한다.")
    @CsvSource(value = {"20:false:100:-100", "21:true:200:-200", "21:false:300:-300",
            "22:false:400:-400"}, delimiter = ':')
    void calculatePrize_bust(int dealerScore, boolean dealerBlackjack, int bettingAmount, int expected) {
        // give
        final PrizeCalculator prizeCalculator = PlayerStatus.BUST.findCalculator();

        // when
        final double actual = prizeCalculator.calculate(22, dealerScore, dealerBlackjack, bettingAmount);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
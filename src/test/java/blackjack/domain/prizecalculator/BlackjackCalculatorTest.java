package blackjack.domain.prizecalculator;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.playerstatus.Blackjack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BlackjackCalculatorTest {

    @ParameterizedTest
    @DisplayName("플레이어가 BLACKJACK일 때 상금을 계산한다.")
    @CsvSource(value = {"20:false:100:150", "21:true:200:0", "21:false:300:450", "22:false:400:600"}, delimiter = ':')
    void calculatePrize_blackjack(int dealerScore, boolean dealerBlackjack, int bettingAmount, int expected) {
        // give
        final PrizeCalculator prizeCalculator = Blackjack.getInstance().findCalculator();

        // when
        final double actual = prizeCalculator.calculate(21, dealerScore, dealerBlackjack, bettingAmount);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
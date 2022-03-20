package blackjack.domain.participant.playerstatus;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BlackjackTest {

    @ParameterizedTest
    @DisplayName("플레이어가 BLACKJACK일 때 상금을 계산한다.")
    @CsvSource(value = {"20:false:100:150", "21:true:200:0", "21:false:300:450", "22:false:400:600"}, delimiter = ':')
    void calculateProfit(int dealerScore, boolean dealerBlackjack, int bettingAmount, int expected) {
        // give
        final CalculableStatus status = Blackjack.getInstance();

        // when
        final double actual = status.calculateProfit(21, dealerScore, dealerBlackjack, bettingAmount);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}

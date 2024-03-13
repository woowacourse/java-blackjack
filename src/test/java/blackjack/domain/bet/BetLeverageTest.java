package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BetLeverageTest {

    @DisplayName("배팅 결과로 플레이어 수익을 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"LUCKY,1000,1500", "WIN,1000,1000", "LOSE,1000,-1000", "TIE,1000,0"})
    void applyLeverage(BetLeverage betLeverage, int betAmount, int revenue) {
        // given
        BetAmount batAmount = new BetAmount(betAmount);

        // when
        BetRevenue batRevenue = betLeverage.applyLeverage(batAmount);

        // then
        assertThat(batRevenue).isEqualTo(new BetRevenue(revenue));
    }
}

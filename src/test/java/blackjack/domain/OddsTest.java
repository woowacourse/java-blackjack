package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class OddsTest {

    @ParameterizedTest
    @CsvSource(value = {"true:true:1.5", "true:false:1", "false:true:0", "false:false:-1"}, delimiter = ':')
    @DisplayName("적절한 배당률을 반환하는지")
    void Get_Appropriate_Odd_Ratio(boolean isWin, boolean isBlackJack, double oddsRatio) {
        assertThat(Odds.findOddsRatio(isWin, isBlackJack)).isEqualTo(oddsRatio);
    }
}

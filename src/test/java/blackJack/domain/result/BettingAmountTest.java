package blackJack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {

    @ParameterizedTest()
    @ValueSource(strings = {"0", "-1"})
    void 베팅_금액이_양수가_아닌_경우_예외가_발생한다(int value) {
        BettingAmount bettingAmount = BettingAmount.newByDefault();

        assertThatThrownBy(() -> bettingAmount.startBetting(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 0 이상이어야 합니다.");
    }

    @ParameterizedTest()
    @CsvSource(value = {"BLACK_JACK_WIN,15000", "WIN,10000", "DRAW,0", "LOSE,-10000"})
    void 승부_결과에_따른_수익을_계산한다(BlackJackMatch blackJackMatch, int expectedProfit) {
        BettingAmount bettingAmount = BettingAmount.newByDefault();

        assertThat(bettingAmount.startBetting(10000).calculateProfit(blackJackMatch)).isEqualTo(expectedProfit);
    }
}
package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BettingAmountTest {
    @ParameterizedTest
    @CsvSource(value = {
            "BLACKJACK_WIN, 15000",
            "WIN, 10000",
            "TIE, 0",
            "LOSE, -10000"
    })
    void 베팅금이_10000일때_게임결과에_따라_배팅금액_대비_수익을_계산할_수_있다(GameResult gameResult, long expectedEarning) {
        BettingAmount bettingAmount = new BettingAmount(10000);
        long earningAmount = bettingAmount.calculateEarningAmount(gameResult);
        assertThat(earningAmount).isEqualTo(expectedEarning);
    }

    @Test
    void 배팅금이_0이하의_값인_경우_에러가_발생한다() {
        assertThatThrownBy(() -> new BettingAmount(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}

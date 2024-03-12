package blackjack.domain.gameresult;

import blackjack.domain.gameresult.Profit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProfitTest {
    @DisplayName("수익은 최소 -10_000_000원부터 10_000_000원까지 가능하다")
    @ParameterizedTest
    @ValueSource(doubles = {-10_000_001, 10_000_001})
    void should_ThrowIllegalArgumentException_When_GiveOutRangedBat(double outRangedProfit) {
        assertThatThrownBy(() -> Profit.from(outRangedProfit))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수익은 최소 -10000000부터 10000000까지 가능합니다.");
    }
}

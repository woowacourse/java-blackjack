package blackjack.domain.gameresult;

import blackjack.domain.gameresult.Batting;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BattingTest {
    @DisplayName("배팅은 최소 1원부터 10_000_000원까지 가능하다")
    @ParameterizedTest
    @ValueSource(doubles = {-1, 10_000_001})
    void should_ThrowIllegalArgumentException_When_GiveOutRangedBat(double outRangedBat) {
        assertThatThrownBy(() -> Batting.from(outRangedBat))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅은 최소 1부터 10000000까지 가능합니다.");
    }
}

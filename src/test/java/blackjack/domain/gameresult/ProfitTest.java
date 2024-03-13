package blackjack.domain.gameresult;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ProfitTest {

    @DisplayName("성공 : 배팅 최대금액의 -1배 이상 1.5배 초과의 수익 객체를 생성할 수 있다")
    @ParameterizedTest
    @ValueSource(doubles = {-10_000_000, 15_000_000})
    void should_getProfitObject_When_GiveValidRangedProfit(double validRangedProfit) {
        assertDoesNotThrow(() -> Profit.from(validRangedProfit));
    }

    @DisplayName("실패 : 배팅 최대금액의 -1배 미만 1.5배 초과의 수익 객체는 생성이 불가하다")
    @ParameterizedTest
    @ValueSource(doubles = {-10_000_001, 15_000_001})
    void should_ThrowIllegalArgumentException_When_GiveOutRangedBat(double outRangedProfit) {
        assertThatThrownBy(() -> Profit.from(outRangedProfit))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수익은 최소 -10000000부터 15000000까지 가능합니다.");
    }
}

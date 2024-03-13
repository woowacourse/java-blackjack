package blackjack.domain.gameresult;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BattingTest {
    // TODO 배팅 가능과 불가능 여부 나누어 테스트 해보기
    @DisplayName("배팅실패 : 1원미만 10_000_000원 초과의 배팅액을 넣을 수 없다")
    @ParameterizedTest
    @ValueSource(doubles = {-1, 10_000_001})
    void should_ThrowIllegalArgumentException_When_GiveOutRangedBat(double outRangedBat) {
        assertThatThrownBy(() -> Batting.from(outRangedBat))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅은 최소 1부터 10000000까지 가능합니다.");
    }
}

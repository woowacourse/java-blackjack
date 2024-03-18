package blackjack.domain.gameresult;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BettingTest {
    @DisplayName("배팅 성공 : 1원이상 10_000_000원 이하의 배팅액을 넣을 수 있다")
    @ParameterizedTest
    @ValueSource(doubles = {1, 10_000_000})
    void should_getBettingInstance_When_GiveValidRangedBet(double validRangedBet) {
        assertDoesNotThrow(() -> Betting.from(validRangedBet));
    }

    @DisplayName("배팅 실패 : 1원미만 10_000_000원 초과의 배팅액을 넣을 수 없다")
    @ParameterizedTest
    @ValueSource(doubles = {-1, 10_000_001})
    void should_ThrowIllegalArgumentException_When_GiveOutRangedBet(double outRangedBet) {
        assertThatThrownBy(() -> Betting.from(outRangedBet))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅은 최소 1부터 10000000까지 가능합니다.");
    }
}

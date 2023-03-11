package blackjack.domain.gameplayer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BettingTest {

    @DisplayName("생성 테스트")
    @Test
    void Should_Success_When_Create() {
        assertDoesNotThrow(() -> Betting.of(1000));
    }

    @DisplayName("배팅 금액은 1 ~ 30만원까지 입력받을 수 있다._성공")
    @ParameterizedTest
    @ValueSource(ints = {1, 300000})
    void Should_Success_When_InputBettingBetween0And300000(int betting) {
        assertDoesNotThrow(() -> Betting.of(betting));
    }

    @DisplayName("배팅 금액은 1 ~ 30만원까지 입력받을 수 있다._실패")
    @ParameterizedTest
    @ValueSource(ints = {0, 300001})
    void Should_ThrowException_When_InputBettingNotBetween0And300000(int betting) {
        assertThatThrownBy(() -> Betting.of(betting))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 0원부터 30만원까지 입력받을 수 있습니다.");
    }
}
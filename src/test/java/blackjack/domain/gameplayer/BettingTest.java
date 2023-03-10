package blackjack.domain.gameplayer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BettingTest {

    @DisplayName("생성 테스트")
    @Test
    void Should_Success_When_Create() {
        assertDoesNotThrow(() -> new Betting(1000));
    }

    @DisplayName("배팅 금액은 0 ~ 30만원까지 입력받을 수 있다._성공")
    @ParameterizedTest
    @ValueSource(ints = {0, 300000})
    void Shuold_Success_When_InputBettingBetween0And300000(int betting) {
        assertDoesNotThrow(() -> new Betting(betting));
    }

    @DisplayName("배팅 금액은 0 ~ 30만원까지 입력받을 수 있다._실패")
    @ParameterizedTest
    @ValueSource(ints = {-1, 300001})
    void Shuold_ThrowException_When_InputBettingNotBetween0And300000(int betting) {
        Assertions.assertThatThrownBy(() -> new Betting(betting))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 0원부터 30만원까지 입력받을 수 있습니다.");
    }
}
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

    @DisplayName("배팅 금액을 이미 설정한 경우에는 금액을 바꿀 수 없다.")
    @Test
    void Should_ThrowException_When_ChangeBettingAfterBet() {
        assertThatThrownBy(() -> Betting.of(2000).changeBetting(1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액을 변경할 수 없습니다.");
    }

    @DisplayName("배팅 금액을 설정하지 않은 경우에는 금액을 바꿀 수 있다.")
    @Test
    void Should_Success_When_ChangeBettingBeforeBet() {
        assertThat(Betting.defaultBetting.changeBetting(1000)).isEqualTo(Betting.of(1000));
    }

    @DisplayName("배팅 금액을 더할 수 있다.")
    @Test
    void Should_Success_When_AddBetting() {
        assertThat(Betting.defaultBetting.addBetting(3000).addBetting(200)).isEqualTo(Betting.of(3200));
    }
}
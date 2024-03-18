package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.status.WinStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerBettingTest {

    @DisplayName("승리 여부를 배팅금액에 적용한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "WIN, 1000",
            "LOSE, -1000",
            "WIN_BLACKJACK, 1500",
            "DRAW, 0",})
    void applyWinStatus(WinStatus winStatus, int expected) {
        // given
        PlayerBetting playerBetting = PlayerBetting.create("kirby", 1000);

        // when
        PlayerBetting bettingResult = playerBetting.applyWinStatus(winStatus.getBetMultiplier());

        // then
        assertThat(bettingResult.getBetting()).isEqualTo(expected);
    }
}

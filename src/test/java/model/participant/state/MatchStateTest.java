package model.participant.state;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchStateTest {

    @Test
    @DisplayName("WIN 상태일 때에는 베팅한 금액을 반환한다.")
    void calculateEarnings_ShouldReturnOriginalValue_WhenWin() {
        MatchState winState = MatchState.WIN;
        int originalBetting = 100;

        assertEquals(originalBetting, winState.calculateEarnings(originalBetting));
    }

    @Test
    @DisplayName("LOSE 상태일 때에는 베팅한 금액의 음수를 반환한다.")
    void calculateEarnings_ShouldReturnOriginalValueWithNegative_WhenLOSE() {
        MatchState loseState = MatchState.LOSE;
        int originalBetting = 100;

        assertEquals(-100, loseState.calculateEarnings(originalBetting));
    }

    @Test
    @DisplayName("BLACK_JACK 상태일 때에는 베팅한 금액의 1.5배를 반환한다.")
    void calculateEarnings_ShouldReturnOriginalValueWithMultiply_WhenBLACK_JACK() {
        MatchState blackJackState = MatchState.BLACK_JACK;
        int originalBetting = 100;

        assertEquals(150, blackJackState.calculateEarnings(originalBetting));
    }

    @Test
    @DisplayName("결과가 나오지 않은 상태일 때에 계산을 요구하면 예외를 던진다.")
    void calculateEarnings_ShouldReturn0_WhenNotAResult() {
        MatchState playingState = MatchState.PLAYING;
        MatchState turnOverState = MatchState.TURNOVER;
        int originalBetting = 100;

        assertAll(() -> {
            assertThrows(IllegalStateException.class, () -> playingState.calculateEarnings(originalBetting));
            assertThrows(IllegalStateException.class, () -> turnOverState.calculateEarnings(originalBetting));
        });
    }

}

package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingAmountTest {

    @Test
    @DisplayName("베팅 금액이 0일때 예외를 던진다.")
    void validateMinus_InputZero_ThrowsException() {
        Integer testBettingAmount = 0;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new BettingAmount(testBettingAmount);
        });
        assertEquals("베팅 금액은 음수일 없습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("베팅 금액이 음수일 때 예외를 던진다..")
    void validateMinus_InputMinus_ThrowsException() {
        Integer testBettingAmount = -1000;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new BettingAmount(testBettingAmount);
        });
        assertEquals("베팅 금액은 음수일 없습니다.", exception.getMessage());
    }

}

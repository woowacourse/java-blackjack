package dto.request;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountRequestDtoTest {

    @Test
    @DisplayName("베팅 금액 입력 문자열이 null이면 에러 발생 검증")
    void betAmount_is_not_null() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BetAmountRequestDto(null));

    }

    @Test
    @DisplayName("베팅 금액 입력 문자열이 빈 문자열(\"\")이면 에러 발생 검증")
    void betAmount_is_not_blank() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BetAmountRequestDto(""));
    }
}
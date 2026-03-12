package dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingAmountRequestDtoTest {

    @Test
    @DisplayName("베팅 금액 입력 문자열이 null이면 에러 발생 검증")
    void 베팅_금액_null_입력_에러_발생_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BettingAmountRequestDto(null));
    }

    @Test
    @DisplayName("베팅 금액 입력 문자열이 빈 문자열(\"\")이면 에러 발생 검증")
    void 베팅_금액_공백_입력_에러_발생_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BettingAmountRequestDto(""));
    }
}

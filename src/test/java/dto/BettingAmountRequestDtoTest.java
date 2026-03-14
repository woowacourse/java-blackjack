package dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.requestDto.BettingAmountRequestDto;

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

    @Test
    @DisplayName("베팅 금액 입력 문자열이 숫자가 아닌 문자면 에러 발생 검증")
    void 베팅_금액_숫자_검증_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BettingAmountRequestDto("QWER"));
    }

    @Test
    @DisplayName("베팅 금액 입력 문자열이 양수가 아닌 문자면 에러 발생 검증")
    void 베팅_금액_양수_검증_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BettingAmountRequestDto("-1000"));
    }
}

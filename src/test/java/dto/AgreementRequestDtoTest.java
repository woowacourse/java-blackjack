package dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.requestDto.AgreementRequestDto;

class AgreementRequestDtoTest {

    @Test
    @DisplayName("y/n 입력 문자열이 null이면 에러 발생 검증")
    void agreement_is_not_null() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new AgreementRequestDto(null));

    }

    @Test
    @DisplayName("y/n 입력 문자열이 빈 문자열(\"\")이면 에러 발생 검증")
    void agreement_is_not_blank() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new AgreementRequestDto(""));
    }

    @Test
    @DisplayName("y/n 입력 문자열이 y 또는 n 이 아닌 경우 에러 발생 검증")
    void agreement_is_y_or_n() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new AgreementRequestDto("Q"));
    }
}

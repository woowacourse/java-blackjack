package dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.requestDto.NameRequestDto;

class NameRequestDtoTest {

    @Test
    @DisplayName("이름 입력 문자열이 null이면 에러 발생 검증")
    void name_is_not_null() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> NameRequestDto.from(null));

    }

    @Test
    @DisplayName("이름 입력 문자열이 빈 문자열(\"\")이면 에러 발생 검증")
    void name_is_not_blank() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> NameRequestDto.from(""));
    }
}

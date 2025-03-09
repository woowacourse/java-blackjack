package view;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @Test
    @DisplayName("중복된 입력이 있으면 예외를 반환합니다.")
    void validateDuplicateTest() {
        //given
        List<String> testNames = List.of("안녕", "안녕", "반가워");

        //when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputValidator.validateDuplicate(testNames));
    }

    @ParameterizedTest
    @DisplayName("잘못된 이름 입력이면 예외를 반환힙나디.")
    @ValueSource(strings = {",,,,", "11,22", ",안녕", "", "안녕,반가워,"})
    void validateInputFormat(String value) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputValidator.validateInputFormat(value));
    }
}
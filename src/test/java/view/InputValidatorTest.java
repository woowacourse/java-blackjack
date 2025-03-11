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
    @ValueSource(strings = {",,,,", "", "안녕,반가워,", ",하이,굿"})
    void validateInputFormatTest(String value) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputValidator.validateInputFormat(value));
    }

    @ParameterizedTest
    @DisplayName("잘못된 유저 응답이면 예외를 반환합니다.")
    @ValueSource(strings = {"N", "Y", "yes", "no", "", " "})
    void validateUserResponseErrorTest(String value) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputValidator.validateUserResponse(value));
    }

    @ParameterizedTest
    @DisplayName("올바른 유저 응답이면 예외를 반환하지 않습니다.")
    @ValueSource(strings = {"n", "y"})
    void validateUserResponseTest(String value) {
        Assertions.assertDoesNotThrow(() -> InputValidator.validateUserResponse(value));
    }
}
package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;


class InputValidatorTest {

    @Test
    @DisplayName("중복된 이름입력에 대한 테스트 케이스에 대해서는 예외를 반환한다.")
    void testDuplicateNameParseCase() {
        List<String> inputNames = List.of("pobi", "pobi");
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputValidator.validateInputNames(inputNames));
    }


    @Test
    @DisplayName("공백 입력에 대한 테스트 케이스에 대해서는 예외를 반환한다.")
    void testEmptyNameParseCase() {
        List<String> inputNames = List.of("pobi", "", "woni");
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputValidator.validateInputNames(inputNames));
    }

    @Test
    @DisplayName("플레이어의 수는 1명 이상 8명 이하여야 한다.")
    void testPlayerCountsParseCase() {
        List<String> inputNames = List.of("유저1", "유저2", "유저3", "유저4", "유저5", "유저6", "유저7", "유저8", "유저9");
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputValidator.validateInputNames(inputNames));
    }


}

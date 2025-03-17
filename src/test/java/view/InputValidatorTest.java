package view;

import java.util.List;
import org.assertj.core.api.Assertions;
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
        Assertions.assertThatThrownBy(() -> InputValidator.validateDuplicate(testNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력은 중복될 수 없습니다.");
    }

    @ParameterizedTest
    @DisplayName("잘못된 이름 입력이면 예외를 반환힙나디.")
    @ValueSource(strings = {",,,,", "", "안녕,반가워,", ",하이,굿"})
    void validateInputFormatTest(String value) {
        Assertions.assertThatThrownBy(() -> InputValidator.validateInputFormat(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("쉼표로 구분되지 않았습니다.");
    }

    @ParameterizedTest
    @DisplayName("배팅 입력이 정수가 아니면 예외를 반환합니다.")
    @ValueSource(strings = {"n", "y", " ", "ㅋㅋ"})
    void validateInputMoneyTest(String value) {
        Assertions.assertThatThrownBy(() -> InputValidator.validateInputMoney(value))
                .isInstanceOf(NumberFormatException.class)
                .hasMessage("숫자가 아닙니다.");
    }

    @ParameterizedTest
    @DisplayName("배팅 입력이 정수범위를 초과하면 예외를 반환합니다.")
    @ValueSource(strings = {"-2147483649", "2147483649"})
    void validateIntegerRangeTest(String value) {
        Assertions.assertThatThrownBy(() -> InputValidator.validateIntegerRange(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용된 숫자 범위가 아닙니다.");
    }
}

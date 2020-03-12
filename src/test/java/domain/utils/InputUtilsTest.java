package domain.utils;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import utils.InputUtils;

public class InputUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"u", "1", ""})
    @DisplayName("입력이 y나 n가 아닐 때 예외를 잘 처리하는지")
    void inputUtilTestWithWrongInput(String input) {
        assertThatThrownBy(() ->
            InputUtils.isAnswerHit(input)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"y:true", "n:false"}, delimiter = ':')
    @DisplayName("입력이 y나 n일 때 boolean 값을 잘 뱉어내는지")
    void inputUtilTestWithRightInput(String input, boolean expected) {
        assertThat(InputUtils.isAnswerHit(input)).isEqualTo(expected);
    }

}

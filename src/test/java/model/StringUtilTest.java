package model;

import exception.IllegalStringInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.StringUtils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringUtilTest {

    @ParameterizedTest
    @ValueSource(strings = {"  ", ""})
    @DisplayName("입력으로 빈값이 들어올 때")
    void input_Empty(String input) {
        assertThatThrownBy(() -> StringUtils.validateEmpty(input))
                .isInstanceOf(IllegalStringInputException.class)
                .hasMessageMatching("입력은 한 글자 이상이어야 합니다.");
    }

    @Test
    @DisplayName("입력으로 null값이 들어올 때")
    void input_Null() {
        assertThatThrownBy(() -> StringUtils.validateNull(null))
                .isInstanceOf(IllegalStringInputException.class)
                .hasMessageMatching("Null 값은 입력하실 수 없습니다.");
    }
}

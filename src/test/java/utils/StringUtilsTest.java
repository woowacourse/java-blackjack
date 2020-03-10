package utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class StringUtilsTest {

    @Test
    @DisplayName("string 형식의 입력을 리스트로 반환 테스트")
    void splitIntoIntListTest() {
        List<String> result = new ArrayList<>(Arrays.asList("오렌지", "히히", "시카"));
        assertThat(StringUtils.splitIntoList("오렌지,히히, 시카"))
            .isEqualTo(result);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("null, empty 입력시 예외처리")
    void nullAndEmptyTest(String input) {
        assertThatThrownBy(() -> StringUtils.splitIntoList(input)).
            isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("공백 입력시 예외처리")
    void blankInputTest() {
        assertThatThrownBy(() -> StringUtils.splitIntoList(" ")).
            isInstanceOf(NullPointerException.class);
    }
}

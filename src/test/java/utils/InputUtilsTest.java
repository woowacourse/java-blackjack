package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InputUtilsTest {
    @Test
    @DisplayName("이름이 제대로 분리되는지 테스트")
    public void splitAsCommaTest() {
        List<String> names = InputUtils.splitAsDelimiter("pobi,json,java");
        assertThat(names).contains("pobi").contains("java");
    }
}
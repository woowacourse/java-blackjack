package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AnswerTypeTest {
    @DisplayName("잘못된 대답을 입력했을 때 예외 출력 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"123", "qwe", "a"})
    void findAnswerTypeTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            AnswerType.findAnswerType(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}

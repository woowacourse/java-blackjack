package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AnswerTest {
    @DisplayName("잘못된 대답을 입력했을 때 예외 출력 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"123", "qwe", "a"})
    void ofWithInvalidAnswerTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            Answer.of(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("y에 대한 정상 대답 반환 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"y", "Y"})
    void ofWithYesAnswerTest(String input) {
        Answer answer = Answer.of(input);

        Assertions.assertThat(answer).isEqualTo(Answer.YES);
    }

    @DisplayName("n에 대한 정상 대답 반환 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"n", "N"})
    void ofWithNoAnswerTest(String input) {
        Answer answer = Answer.of(input);

        Assertions.assertThat(answer).isEqualTo(Answer.NO);
    }
}

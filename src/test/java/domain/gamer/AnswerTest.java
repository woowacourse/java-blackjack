package domain.gamer;

import domain.result.Answer;
import exception.AnswerFormatException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class AnswerTest {
    @Test
    @DisplayName("y 또는 n이 아닌 다른 문자가 들어오는 경우 예외처리 테스트")
    void illegalAnswerTest() {
        String answer = "a";
        Assertions.assertThatThrownBy(() -> Answer.findAnswer(answer))
                .isInstanceOf(AnswerFormatException.class);
    }

    @ParameterizedTest
    @MethodSource("generateAnswer")
    @DisplayName("y 또는 n이 제대로 들어오는 경우 테스트")
    void correctYesOrNoTest(String answer, Answer yesOrNo) {
        Assertions.assertThat(Answer.findAnswer(answer)).isEqualTo(yesOrNo);
    }

    static Stream<Arguments> generateAnswer() {
        return Stream.of(
                Arguments.of("y", Answer.YES),
                Arguments.of("n", Answer.NO),
                Arguments.of("Y", Answer.YES),
                Arguments.of("N", Answer.NO));
    }
}

package domain.gamer;

import exception.AnswerFormatException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class AddCardAnswerTest {
    @Test
    @DisplayName("y 또는 n이 아닌 다른 문자가 들어오는 경우 예외처리 테스트")
    void notYesOrNoTest() {
        String answer = "a";
        Assertions.assertThatThrownBy(() -> AddCardAnswer.isYes(answer))
                .isInstanceOf(AnswerFormatException.class);
    }

    @ParameterizedTest
    @MethodSource("generateYesOrNo")
    @DisplayName("y 또는 n이 제대로 들어오는 경우 테스트")
    void correctYesOrNoTest(String answer, boolean isYes) {
        Assertions.assertThat(AddCardAnswer.isYes(answer)).isEqualTo(isYes);
    }

    static Stream<Arguments> generateYesOrNo() {
        return Stream.of(
                Arguments.of("y", true),
                Arguments.of("n", false),
                Arguments.of("Y", true),
                Arguments.of("N", false));
    }
}

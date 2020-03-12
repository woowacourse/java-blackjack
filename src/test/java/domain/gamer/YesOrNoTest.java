package domain.gamer;

import exception.YesOrNoFormatException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class YesOrNoTest {
    @Test
    @DisplayName("y 또는 n이 아닌 다른 문자가 들어오는 경우 예외처리 테스트")
    void notYesOrNoTest() {
        String answer = "a";
        Assertions.assertThatThrownBy(() -> YesOrNo.findYesOrNo(answer))
                .isInstanceOf(YesOrNoFormatException.class);
    }

    @ParameterizedTest
    @MethodSource("generateYesOrNo")
    @DisplayName("y 또는 n이 제대로 들어오는 경우 테스트")
    void correctYesOrNoTest(String answer, YesOrNo yesOrNo) {
        Assertions.assertThat(YesOrNo.findYesOrNo(answer)).isEqualTo(yesOrNo);
    }

    static Stream<Arguments> generateYesOrNo() {
        return Stream.of(
                Arguments.of("y", YesOrNo.YES),
                Arguments.of("n", YesOrNo.NO),
                Arguments.of("Y", YesOrNo.YES),
                Arguments.of("N", YesOrNo.NO));
    }
}

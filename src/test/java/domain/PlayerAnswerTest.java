package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
public class PlayerAnswerTest {

    @ParameterizedTest
    @NullAndEmptySource
    void null이나_빈문자열로_enum_클래스를_찾을때_예외발생확인_테스트(String input) {
        Assertions.assertThatThrownBy(() -> PlayerAnswer.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y와 n만 입력 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"yes", "no", "Y", "N", "O", "ㅇㅇ", "ㄴㄴ"})
    void y나_n이_아닌_입력값으로_enum_클래스를_찾을때_예외발생확인_테스트(String input) {
        Assertions.assertThatThrownBy(() -> PlayerAnswer.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y와 n만 입력 가능합니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"y,true", "n,false"})
    void YES_enum_클래스와_일치여부확인_테스트(String input, boolean expected) {
        Assertions.assertThat(PlayerAnswer.of(input).isYes()).isEqualTo(expected);
    }
}

package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("예/아니오 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AnswerTest {

    @ParameterizedTest
    @CsvSource(value = {"y:true", "n:false"}, delimiterString = ":")
    void y를_입력하면_true_n을_입력하면_false를_반환한다(String input, boolean expected) {
        assertThat(Answer.selectAnswer(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {":", " ", "Y", "N", "예", "아니오"})
    void y와n이아닌_다른입력값이면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> Answer.selectAnswer(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 예는 y, 아니오는 n을 입력하세요.");
    }
}

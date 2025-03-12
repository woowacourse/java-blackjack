package view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.IllegalBlackjackInputException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class InputConverterTest {

    @DisplayName("구분자(,)를 기준으로 파싱한다.")
    @Test
    void test1() {
        String input = "pobi,jason,danny";
        List<String> expected = List.of("pobi", "jason", "danny");
        assertThat(InputConverter.splitByDelimiter(input, ","))
                .containsExactlyElementsOf(expected);
    }

    @DisplayName("입력이 null이나 blank라면 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void test2(final String input) {
        assertThatThrownBy(() -> InputConverter.splitByDelimiter(input, ","))
                .isInstanceOf(IllegalBlackjackInputException.class);
    }
}

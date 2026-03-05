package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InputParserTest {
    InputParser inputParser;

    @BeforeEach
    void setUp() {
        inputParser = new InputParser();
    }

    @Test
    void 이름을_입력하면_쉼표를_기준으로_분리한다() {
        String input = "pobi,jason";

        List<String> names = inputParser.parseName(input);

        assertThat(names.size()).isEqualTo(2);
        assertThat(names.get(0)).isEqualTo("pobi");
        assertThat(names.get(1)).isEqualTo("jason");
    }

    @Test
    void 컴마가_맨앞에_있으면_컴마를_제거하고_분리한다() {
        String input = ",pobi,jason";

        List<String> names = inputParser.parseName(input);

        assertThat(names.size()).isEqualTo(2);
        assertThat(names.get(0)).isEqualTo("pobi");
        assertThat(names.get(1)).isEqualTo("jason");
    }

    @Test
    void 컴마가_중복되는_경우_제거하고_분리한다() {
        String input = ",pobi,,,jason";

        List<String> names = inputParser.parseName(input);

        assertThat(names.size()).isEqualTo(2);
        assertThat(names.get(0)).isEqualTo("pobi");
        assertThat(names.get(1)).isEqualTo("jason");
    }

    @Test
    void 입력이_빈_경우_예외를_발생한다() {
        String input = "";

        assertThatThrownBy(() -> inputParser.parseName(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력이_공백인_경우_예외를_발생한다() {
        String input = "  ";

        assertThatThrownBy(() -> inputParser.parseName(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        String[] names = inputParser.parseName(input);

        assertThat(names.length).isEqualTo(2);
        assertThat(names[0]).isEqualTo("pobi");
        assertThat(names[1]).isEqualTo("jason");
    }

    @Test
    void 컴마가_맨앞에_있으면_컴마를_제거하고_분리한다() {
        String input = ",pobi,jason";

        String[] names = inputParser.parseName(input);

        assertThat(names.length).isEqualTo(2);
        assertThat(names[0]).isEqualTo("pobi");
        assertThat(names[1]).isEqualTo("jason");
    }

}

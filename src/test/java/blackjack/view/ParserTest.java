package blackjack.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParserTest {

    @Test
    void 구분자를_기준으로_문자열을_파싱한다() {
        final String input = "pobi,jason";

        final List<String> result = Parser.parseByDelimiter(input, ",");

        assertThat(result).containsExactly("pobi", "jason");
    }

    @Test
    void 구분자를_기준으로_빈문자열을_파싱한다() {
        final String input = ",,";

        final List<String> result = Parser.parseByDelimiter(input, ",");

        assertThat(result).containsExactly("", "", "");
    }
}

class Parser {

    public static List<String> parseByDelimiter(final String value, final String delimiter) {
        return Arrays.asList(value.split(delimiter, -1));
    }
}
